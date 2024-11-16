package org.example.adapter.presenter;

import org.example.adapter.ui.ChartScreen;
import org.example.domain.boundaries.in.GetAnalystInputBoundary;
import org.example.domain.boundaries.out.GetAnalystOutputBoundary;
import org.example.domain.entities.models.AnalystResponse;

public class ChartPresenter implements GetAnalystOutputBoundary {
    private final ChartScreen chartScreen; 
    private final GetAnalystInputBoundary getAnalystInputBoundary;

    public ChartPresenter(ChartScreen chartScreen, GetAnalystInputBoundary getAnalystInputBoundary) {
        this.chartScreen = chartScreen;
        this.getAnalystInputBoundary = getAnalystInputBoundary;
    }
    
    @Override
    public void showAnalyst(AnalystResponse analystResponse) {
        chartScreen.showAnalyst(analystResponse);
    }

    public void getAnalyst() {
        getAnalystInputBoundary.execute();
    }
}
