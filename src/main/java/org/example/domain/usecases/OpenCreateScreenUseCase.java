package org.example.domain.usecases;

import org.example.domain.boundaries.in.OpenStateManageCreateScreenInputBoundary;
import org.example.domain.boundaries.out.OpenCreateScreenOutputBoundary;

public class OpenCreateScreenUseCase implements OpenStateManageCreateScreenInputBoundary {
    private final OpenCreateScreenOutputBoundary outputBoundary;

    public OpenCreateScreenUseCase(OpenCreateScreenOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute() {
        outputBoundary.openCreateScreen();
    }
}
