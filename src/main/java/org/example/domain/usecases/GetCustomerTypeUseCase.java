package org.example.domain.usecases;

import org.example.domain.boundaries.in.GetCustomerTypeInputBoundary;
import org.example.domain.boundaries.out.CustomerTypeRepository;
import org.example.domain.boundaries.out.GetCustomerTypeOutputBoundary;

import java.util.List;

public class GetCustomerTypeUseCase implements GetCustomerTypeInputBoundary {
    private final GetCustomerTypeOutputBoundary outputBoundary;
    private final CustomerTypeRepository customerTypeRepository;
    
    public GetCustomerTypeUseCase(GetCustomerTypeOutputBoundary outputBoundary, CustomerTypeRepository customerTypeRepository) {
        this.outputBoundary = outputBoundary;
        this.customerTypeRepository = customerTypeRepository;
    }
    @Override
    public void execute() {
        List<String> customerTypes = customerTypeRepository.getCustomerTypes();
        outputBoundary.setCustomerType(customerTypes);
    }
}
