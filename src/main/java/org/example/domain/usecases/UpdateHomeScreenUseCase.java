package org.example.domain.usecases;

import org.example.domain.boundaries.in.UpdateHomeScreenInputBoundary;
import org.example.domain.boundaries.out.UpdateHomeScreenOutputBoundary;

public class UpdateHomeScreenUseCase implements UpdateHomeScreenInputBoundary {
    private final UpdateHomeScreenOutputBoundary outputBoundary; 
    
    public UpdateHomeScreenUseCase(UpdateHomeScreenOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }
    @Override
    public void execute() {
        System.out.println("update home");
        outputBoundary.updateHomeScreen();
    }
}
