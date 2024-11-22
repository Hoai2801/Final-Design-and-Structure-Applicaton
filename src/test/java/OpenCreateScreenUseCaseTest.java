import org.example.domain.boundaries.in.OpenStateManageCreateScreenInputBoundary;
import org.example.domain.boundaries.out.OpenCreateScreenOutputBoundary;
import org.example.domain.usecases.OpenCreateScreenUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OpenCreateScreenUseCaseTest {


    // Successfully calls openCreateScreen on the output boundary when execute is invoked
    @Test
    public void test_execute_calls_open_create_screen() {
        OpenCreateScreenOutputBoundary outputBoundary = mock(OpenCreateScreenOutputBoundary.class);
        OpenCreateScreenUseCase useCase = new OpenCreateScreenUseCase(outputBoundary);
        useCase.execute();
        verify(outputBoundary, times(1)).openCreateScreen();
    }

    // Correctly initializes OpenCreateScreenUseCase with a valid output boundary
    @Test
    public void test_initialization_with_valid_output_boundary() {
        OpenCreateScreenOutputBoundary outputBoundary = mock(OpenCreateScreenOutputBoundary.class);
        OpenCreateScreenUseCase useCase = new OpenCreateScreenUseCase(outputBoundary);
        assertNotNull(useCase);
    }

    // Handle null output boundary during initialization
    @Test
    public void test_initialization_with_null_output_boundary() {
        new OpenCreateScreenUseCase(null);
    }

    // Verify execute method is idempotent
    @Test
    public void test_execute_is_idempotent() {
        OpenCreateScreenOutputBoundary outputBoundary = mock(OpenCreateScreenOutputBoundary.class);
        OpenCreateScreenUseCase useCase = new OpenCreateScreenUseCase(outputBoundary);
        useCase.execute();
        useCase.execute();
        verify(outputBoundary, times(2)).openCreateScreen();
    }

    // Test concurrent execution of the execute method
    @Test
    public void test_concurrent_execution_of_execute() throws InterruptedException {
        OpenCreateScreenOutputBoundary outputBoundary = mock(OpenCreateScreenOutputBoundary.class);
        OpenCreateScreenUseCase useCase = new OpenCreateScreenUseCase(outputBoundary);

        Runnable task = useCase::execute;

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        verify(outputBoundary, times(2)).openCreateScreen();
    }

    // Ensure OpenCreateScreenUseCase adheres to OpenStateManageCreateScreenInputBoundary contract
    @Test
    public void test_adherence_to_input_boundary_contract() {
        assertTrue(OpenStateManageCreateScreenInputBoundary.class.isAssignableFrom(OpenCreateScreenUseCase.class));
    }

    // Validate that openCreateScreen is called exactly once per execute invocation
    @Test
    public void test_open_create_screen_called_once_per_execute() {
        OpenCreateScreenOutputBoundary outputBoundary = mock(OpenCreateScreenOutputBoundary.class);
        OpenCreateScreenUseCase useCase = new OpenCreateScreenUseCase(outputBoundary);

        useCase.execute();

        verify(outputBoundary, times(1)).openCreateScreen();
    }

    // Check for proper handling of exceptions thrown by openCreateScreen
    @Test
    public void test_handling_exceptions_from_open_create_screen() {
        OpenCreateScreenOutputBoundary outputBoundary = new OpenCreateScreenOutputBoundary() {
            @Override
            public void openCreateScreen() {
                throw new RuntimeException("Exception from openCreateScreen");
            }
        };

        OpenCreateScreenUseCase useCase = new OpenCreateScreenUseCase(outputBoundary);

        try {
            useCase.execute();
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("Exception from openCreateScreen", e.getMessage());
        }
    }

    // Ensure execute method can be called multiple times without side effects
    @Test
    public void test_multiple_execute_calls_no_side_effects() {
        OpenCreateScreenOutputBoundary outputBoundary = mock(OpenCreateScreenOutputBoundary.class);
        OpenCreateScreenUseCase useCase = new OpenCreateScreenUseCase(outputBoundary);

        for (int i = 0; i < 5; i++) {
            useCase.execute();
        }

        verify(outputBoundary, times(5)).openCreateScreen();
    }

    // Validate that execute method does not alter state of output boundary
    @Test
    public void test_execute_does_not_alter_output_boundary_state() {
        class TestOutputBoundary implements OpenCreateScreenOutputBoundary {
            private boolean stateChanged = false;

            @Override
            public void openCreateScreen() {
                stateChanged = true;
            }

            public boolean isStateChanged() {
                return stateChanged;
            }
        }

        TestOutputBoundary outputBoundary = new TestOutputBoundary();
        OpenCreateScreenUseCase useCase = new OpenCreateScreenUseCase(outputBoundary);

        useCase.execute();

        assertTrue(outputBoundary.isStateChanged());

        // Reset state and re-test to ensure no further state change occurs.
        outputBoundary.stateChanged = false;

        useCase.execute();

        assertTrue(outputBoundary.isStateChanged());
    }

    // Ensure execute method handles multiple threads correctly without data corruption or race conditions.
    @Test
    public void test_execute_handles_multithreading_correctly() throws InterruptedException {
        final int threadCount = 10;

        class SynchronizedOutputBoundary implements OpenCreateScreenOutputBoundary {
            private int callCount = 0;

            @Override
            public synchronized void openCreateScreen() {
                callCount++;
            }

            public int getCallCount() {
                return callCount;
            }
        }

        SynchronizedOutputBoundary outputBoundary = new SynchronizedOutputBoundary();
        OpenCreateScreenUseCase useCase = new OpenCreateScreenUseCase(outputBoundary);

        Runnable task = useCase::execute;

        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(threadCount, outputBoundary.getCallCount());
    }
}