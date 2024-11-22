

import org.example.domain.boundaries.out.GetNationalityOutputBoundary;
import org.example.domain.boundaries.out.NationalityRepository;
import org.example.domain.usecases.GetNationalityUseCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class GetNationalityUseCaseTest {


    // Executes successfully when valid outputBoundary and nationalityRepository are provided
    @Test
    public void test_execute_successfully_with_valid_dependencies() {
        GetNationalityOutputBoundary outputBoundary = nationality -> {};
        NationalityRepository nationalityRepository = () -> List.of("American", "Canadian");
        GetNationalityUseCase useCase = new GetNationalityUseCase(outputBoundary, nationalityRepository);
        useCase.execute();
    }

    // Retrieves a list of nationalities from the nationalityRepository
    @Test
    public void test_retrieve_nationalities_from_repository() {
        List<String> expectedNationalities = List.of("American", "Canadian");
        NationalityRepository nationalityRepository = () -> expectedNationalities;
        GetNationalityOutputBoundary outputBoundary = nationality -> assertEquals(expectedNationalities, nationality);
        GetNationalityUseCase useCase = new GetNationalityUseCase(outputBoundary, nationalityRepository);
        useCase.execute();
    }

    // Sets the retrieved nationalities to the outputBoundary
    @Test
    public void test_set_retrieved_nationalities_to_output_boundary() {
        List<String> expectedNationalities = List.of("American", "Canadian");
        NationalityRepository nationalityRepository = () -> expectedNationalities;
        GetNationalityOutputBoundary outputBoundary = nationality -> assertEquals(expectedNationalities, nationality);
        GetNationalityUseCase useCase = new GetNationalityUseCase(outputBoundary, nationalityRepository);
        useCase.execute();
    }

    // Handles empty list returned by nationalityRepository gracefully
    @Test
    public void test_handle_empty_list_from_repository() {
        NationalityRepository nationalityRepository = List::of;
        GetNationalityOutputBoundary outputBoundary = nationality -> assertTrue(nationality.isEmpty());
        GetNationalityUseCase useCase = new GetNationalityUseCase(outputBoundary, nationalityRepository);
        useCase.execute();
    }

    // Manages null return from nationalityRepository without exceptions
    @Test
    public void test_manage_null_return_from_repository() {
        NationalityRepository nationalityRepository = () -> null;
        GetNationalityOutputBoundary outputBoundary = nationality -> assertNull(nationality);
        GetNationalityUseCase useCase = new GetNationalityUseCase(outputBoundary, nationalityRepository);
        useCase.execute();
    }

    // Validates that execute method is called only once per instance
    @Test
    public void test_execute_called_once_per_instance() {
        AtomicInteger callCount = new AtomicInteger(0);
        NationalityRepository nationalityRepository = () -> {
            callCount.incrementAndGet();
            return List.of("American", "Canadian");
        };
        GetNationalityOutputBoundary outputBoundary = nationality -> {};
        GetNationalityUseCase useCase = new GetNationalityUseCase(outputBoundary, nationalityRepository);
        useCase.execute();
        assertEquals(1, callCount.get());
    }

    // Ensures outputBoundary is not modified before execute is called
    @Test
    public void test_output_boundary_not_modified_before_execute() {
        List<String> initialList = new ArrayList<>();
        GetNationalityOutputBoundary outputBoundary = initialList::addAll;
        NationalityRepository nationalityRepository = () -> List.of("American", "Canadian");
        new GetNationalityUseCase(outputBoundary, nationalityRepository);
        assertTrue(initialList.isEmpty());
    }

    // Confirms that nationalityRepository is accessed only during execute
    @Test
    public void test_repository_accessed_only_during_execute() {
        AtomicBoolean accessed = new AtomicBoolean(false);
        NationalityRepository nationalityRepository = () -> {
            accessed.set(true);
            return List.of("American", "Canadian");
        };
        GetNationalityOutputBoundary outputBoundary = nationality -> {};
        new GetNationalityUseCase(outputBoundary, nationalityRepository);
        assertFalse(accessed.get());
    }

    // Verifies that execute does not alter the state of nationalityRepository
    @Test
    public void test_execute_does_not_alter_repository_state() {
        List<String> originalList = List.of("American", "Canadian");
        NationalityRepository nationalityRepository = () -> new ArrayList<>(originalList);
        GetNationalityOutputBoundary outputBoundary = nationality -> {};
        GetNationalityUseCase useCase = new GetNationalityUseCase(outputBoundary, nationalityRepository);
        useCase.execute();
        assertEquals(originalList, nationalityRepository.getNationality());
    }

    // Checks for thread safety when execute is called concurrently
    @Test
    public void test_thread_safety_of_execute_method() throws InterruptedException {
        List<String> expectedNationalities = List.of("American", "Canadian");
        NationalityRepository nationalityRepository = () -> expectedNationalities;
        AtomicInteger successCount = new AtomicInteger(0);

        Runnable task = () -> {
            try {
                GetNationalityOutputBoundary outputBoundary = nationality -> assertEquals(expectedNationalities, nationality);
                GetNationalityUseCase useCase = new GetNationalityUseCase(outputBoundary, nationalityRepository);
                useCase.execute();
                successCount.incrementAndGet();
            } catch (AssertionError e) {
                // Handle assertion error if any thread fails the check.
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        assertEquals(2, successCount.get());
    }
}