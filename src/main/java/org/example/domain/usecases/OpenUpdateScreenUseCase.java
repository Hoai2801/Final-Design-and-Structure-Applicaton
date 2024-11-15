package org.example.domain.usecases;

import org.example.domain.boundaries.in.OpenUpdateScreenInputBoundary;
import org.example.domain.boundaries.out.OpenUpdateScreenOutputBoundary;

public class OpenUpdateScreenUseCase implements OpenUpdateScreenInputBoundary {
    private final OpenUpdateScreenOutputBoundary outputBoundary;
    
    public OpenUpdateScreenUseCase(OpenUpdateScreenOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }
    @Override
    public void execute() {
        outputBoundary.openUpdateScreen();
    }

    @Override
    public void execute(int customerId, String type) {
        outputBoundary.openUpdateScreen(customerId, type);
    }
}
