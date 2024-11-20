package org.example.adapter.controller;

import org.example.domain.boundaries.in.GetAnalystInputBoundary;

public class ChartController {
    private final GetAnalystInputBoundary getAnalystInputBoundary;
    public ChartController(GetAnalystInputBoundary getAnalystInputBoundary) {
        this.getAnalystInputBoundary = getAnalystInputBoundary;
    }

    public void getAnalyst() {
        getAnalystInputBoundary.execute();
    }
}
