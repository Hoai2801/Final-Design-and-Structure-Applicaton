package org.example.domain.usecases;

import org.example.domain.boundaries.in.OpenChartScreenInputBoundary;
import org.example.domain.boundaries.out.OpenChartScreenOutputBoundary;

public class OpenChartScreenUseCase implements OpenChartScreenInputBoundary {
    private final OpenChartScreenOutputBoundary outputBoundary;
    
    public OpenChartScreenUseCase(OpenChartScreenOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute() {
        outputBoundary.openChartScreen();
    }
}
