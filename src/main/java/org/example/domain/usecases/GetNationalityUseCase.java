package org.example.domain.usecases;

import org.example.domain.boundaries.in.GetNationalityInputBoundary;
import org.example.domain.boundaries.out.GetCustomerTypeOutputBoundary;
import org.example.domain.boundaries.out.GetNationalityOutputBoundary;
import org.example.domain.boundaries.out.NationalityRepository;

import java.util.List;

public class GetNationalityUseCase implements GetNationalityInputBoundary {
    private final GetNationalityOutputBoundary outputBoundary;
    private final NationalityRepository nationalityRepository; 
    
    public GetNationalityUseCase(GetNationalityOutputBoundary outputBoundary, NationalityRepository nationalityRepository) {
        this.outputBoundary = outputBoundary;
        this.nationalityRepository = nationalityRepository;
    }
    
    @Override
    public void execute() {
        List<String> nationality = nationalityRepository.getNationality();
        outputBoundary.setNationality(nationality);
    }
}
