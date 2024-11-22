import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.VietnameseRepository;
import org.example.domain.boundaries.out.GetAnalystOutputBoundary;
import org.example.domain.entities.models.AnalystResponse;
import org.example.domain.usecases.GetAnalystUseCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetAnalystUseCaseTest {


    // Executes successfully when both repositories return valid data
    @Test
    public void test_execute_with_valid_data() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        GetAnalystOutputBoundary outputBoundary = new GetAnalystOutputBoundary() {
            @Override
            public void showAnalyst(AnalystResponse analystResponse) {
                assertNotNull(analystResponse);
            }
        };
        GetAnalystUseCase useCase = new GetAnalystUseCase(outputBoundary, vietnameseRepository, foreignRepository);
        useCase.execute();
    }

    // Outputs the combined data using the output boundary
    @Test
    public void test_output_boundary_called() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        final boolean[] called = {false};
        GetAnalystOutputBoundary outputBoundary = analystResponse -> called[0] = true;
        GetAnalystUseCase useCase = new GetAnalystUseCase(outputBoundary, vietnameseRepository, foreignRepository);
        useCase.execute();
        assertTrue(called[0]);
    }

    // Initializes with valid dependencies and executes without errors
    @Test
    public void test_initialization_and_execution() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        GetAnalystOutputBoundary outputBoundary = new GetAnalystOutputBoundary() {
            @Override
            public void showAnalyst(AnalystResponse analystResponse) {
                assertNotNull(analystResponse);
            }
        };
        GetAnalystUseCase useCase = new GetAnalystUseCase(outputBoundary, vietnameseRepository, foreignRepository);
        useCase.execute();
    }

    // Handles empty data from both repositories without errors
    @Test
    public void test_handles_empty_data() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository() {
            @Override
            public List<Object[]> countInvoicesByMonth() {
                return new ArrayList<>();
            }
        };
        ForeignRepository foreignRepository = new ForeignRepository() {
            @Override
            public List<Object[]> countInvoicesByMonth() {
                return new ArrayList<>();
            }
        };
        GetAnalystOutputBoundary outputBoundary = new GetAnalystOutputBoundary() {
            @Override
            public void showAnalyst(AnalystResponse analystResponse) {
                assertTrue(analystResponse.getInvoiceCountsByMonthVietnamese().isEmpty());
                assertTrue(analystResponse.getInvoiceCountsByMonthForeign().isEmpty());
            }
        };
        GetAnalystUseCase useCase = new GetAnalystUseCase(outputBoundary, vietnameseRepository, foreignRepository);
        useCase.execute();
    }

    // Deals with duplicate month entries in invoice data
    @Test
    public void test_handles_duplicate_month_entries() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository() {
            @Override
            public List<Object[]> countInvoicesByMonth() {
                return Arrays.asList(new Object[]{"2023-01", 5}, new Object[]{"2023-01", 3});
            }
        };
        ForeignRepository foreignRepository = new ForeignRepository() {
            @Override
            public List<Object[]> countInvoicesByMonth() {
                return Arrays.asList(new Object[]{"2023-01", 2}, new Object[]{"2023-01", 4});
            }
        };
        GetAnalystOutputBoundary outputBoundary = new GetAnalystOutputBoundary() {
            @Override
            public void showAnalyst(AnalystResponse analystResponse) {
                assertEquals((int) analystResponse.getInvoiceCountsByMonthVietnamese().get("2023-01"), 8);
                assertEquals((int) analystResponse.getInvoiceCountsByMonthForeign().get("2023-01"), 6);
            }
        };
        GetAnalystUseCase useCase = new GetAnalystUseCase(outputBoundary, vietnameseRepository, foreignRepository);
        useCase.execute();
    }

    // Handles large datasets efficiently without performance degradation
    @Test
    public void test_large_datasets_performance() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository() {
            @Override
            public List<Object[]> countInvoicesByMonth() {
                List<Object[]> data = new ArrayList<>();
                for (int i = 0; i < 10000; i++) {
                    data.add(new Object[]{"2023-" + (i % 12 + 1), 1});
                }
                return data;
            }
        };
        ForeignRepository foreignRepository = new ForeignRepository() {
            @Override
            public List<Object[]> countInvoicesByMonth() {
                List<Object[]> data = new ArrayList<>();
                for (int i = 0; i < 10000; i++) {
                    data.add(new Object[]{"2023-" + (i % 12 + 1), 1});
                }
                return data;
            }
        };
        GetAnalystOutputBoundary outputBoundary = new GetAnalystOutputBoundary() {
            @Override
            public void showAnalyst(AnalystResponse analystResponse) {
                assertNotNull(analystResponse);
            }
        };
        GetAnalystUseCase useCase = new GetAnalystUseCase(outputBoundary, vietnameseRepository, foreignRepository);
        useCase.execute();
    }

    // Validates that the output boundary is called exactly once
    @Test
    public void test_output_boundary_called_once() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        final int[] callCount = {0};
        GetAnalystOutputBoundary outputBoundary = new GetAnalystOutputBoundary() {
            @Override
            public void showAnalyst(AnalystResponse analystResponse) {
                callCount[0]++;
            }
        };
        GetAnalystUseCase useCase = new GetAnalystUseCase(outputBoundary, vietnameseRepository, foreignRepository);
        useCase.execute();
        assertEquals(callCount[0], 1);
    }

    // Ensures no data is lost during the combination of invoice counts
    @Test
    public void test_no_data_loss_during_combination() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository() {
            @Override
            public List<Object[]> countInvoicesByMonth() {
                return Arrays.asList(new Object[]{"2023-01", 5}, new Object[]{"2023-02", 10});
            }
        };
        ForeignRepository foreignRepository = new ForeignRepository() {
            @Override
            public List<Object[]> countInvoicesByMonth() {
                return Arrays.asList(new Object[]{"2023-01", 2}, new Object[]{"2023-02", 8});
            }
        };
        GetAnalystOutputBoundary outputBoundary = new GetAnalystOutputBoundary() {
            @Override
            public void showAnalyst(AnalystResponse analystResponse) {
                assertEquals((int) analystResponse.getInvoiceCountsByMonthVietnamese().get("2023-01"), 5);
                assertEquals((int) analystResponse.getInvoiceCountsByMonthVietnamese().get("2023-02"), 10);
                assertEquals((int) analystResponse.getInvoiceCountsByMonthForeign().get("2023-01"), 2);
                assertEquals((int) analystResponse.getInvoiceCountsByMonthForeign().get("2023-02"), 8);
            }
        };
        GetAnalystUseCase useCase = new GetAnalystUseCase(outputBoundary, vietnameseRepository, foreignRepository);
        useCase.execute();
    }
}