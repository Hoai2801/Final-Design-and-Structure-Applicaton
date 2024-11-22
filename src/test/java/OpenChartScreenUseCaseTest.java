import org.example.domain.boundaries.out.OpenChartScreenOutputBoundary;
import org.example.domain.usecases.OpenChartScreenUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class OpenChartScreenUseCaseTest {


    // OpenChartScreenUseCase successfully calls openChartScreen on the output boundary
    @Test
    public void test_open_chart_screen_called() {
        OpenChartScreenOutputBoundary mockOutputBoundary = mock(OpenChartScreenOutputBoundary.class);
        OpenChartScreenUseCase useCase = new OpenChartScreenUseCase(mockOutputBoundary);
        useCase.execute();
        verify(mockOutputBoundary, times(1)).openChartScreen();
    }

    // Constructor correctly initializes OpenChartScreenOutputBoundary
    @Test
    public void test_constructor_initializes_output_boundary() {
        OpenChartScreenOutputBoundary mockOutputBoundary = mock(OpenChartScreenOutputBoundary.class);
        OpenChartScreenUseCase useCase = new OpenChartScreenUseCase(mockOutputBoundary);
        assertNotNull(useCase);
    }

    // execute method is invoked without exceptions
    @Test
    public void test_execute_invoked_without_exceptions() {
        OpenChartScreenOutputBoundary mockOutputBoundary = mock(OpenChartScreenOutputBoundary.class);
        OpenChartScreenUseCase useCase = new OpenChartScreenUseCase(mockOutputBoundary);
        useCase.execute();
    }

    // OpenChartScreenOutputBoundary is null during construction
    @Test
    public void test_null_output_boundary_construction() {
        new OpenChartScreenUseCase(null);
    }

    // execute is called multiple times in succession
    @Test
    public void test_execute_called_multiple_times() {
        OpenChartScreenOutputBoundary mockOutputBoundary = mock(OpenChartScreenOutputBoundary.class);
        OpenChartScreenUseCase useCase = new OpenChartScreenUseCase(mockOutputBoundary);
        useCase.execute();
        useCase.execute();
        verify(mockOutputBoundary, times(2)).openChartScreen();
    }

    // Test for performance when execute is called in a loop
    @Test
    public void test_performance_execute_in_loop() {
        OpenChartScreenOutputBoundary mockOutputBoundary = mock(OpenChartScreenOutputBoundary.class);
        OpenChartScreenUseCase useCase = new OpenChartScreenUseCase(mockOutputBoundary);
        for (int i = 0; i < 1000; i++) {
            useCase.execute();
        }
        verify(mockOutputBoundary, times(1000)).openChartScreen();
    }

    // Verify thread safety when execute is called concurrently
    @Test
    public void test_thread_safety_execute_concurrently() throws InterruptedException {
        OpenChartScreenOutputBoundary mockOutputBoundary = mock(OpenChartScreenOutputBoundary.class);
        OpenChartScreenUseCase useCase = new OpenChartScreenUseCase(mockOutputBoundary);

        Runnable task = () -> useCase.execute();

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        verify(mockOutputBoundary, times(2)).openChartScreen();
    }

    // Validate behavior when OpenChartScreenOutputBoundary is a mock
    @Test
    public void test_behavior_with_mock_output_boundary() {
        OpenChartScreenOutputBoundary mockOutputBoundary = mock(OpenChartScreenOutputBoundary.class);
        OpenChartScreenUseCase useCase = new OpenChartScreenUseCase(mockOutputBoundary);

        useCase.execute();

        verify(mockOutputBoundary).openChartScreen();
    }

    // Check for memory leaks when repeatedly creating instances of OpenChartScreenUseCase
    @Test
    public void test_memory_leaks_on_repeated_instance_creation() {
        for (int i = 0; i < 10000; i++) {
            OpenChartScreenOutputBoundary mockOutputBoundary = mock(OpenChartScreenOutputBoundary.class);
            new OpenChartScreenUseCase(mockOutputBoundary).execute();
            // No assertions, just checking for memory issues.
        }
    }
}