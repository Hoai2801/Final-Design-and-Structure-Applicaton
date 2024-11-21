package org.example.adapter.presenter;

import org.example.adapter.ui.ChartScreen;
import org.example.domain.boundaries.out.GetAnalystOutputBoundary;
import org.example.domain.entities.models.AnalystResponse;

public class ChartPresenter implements GetAnalystOutputBoundary {
    private final ChartScreen chartScreen; 

    public ChartPresenter(ChartScreen chartScreen) {
        this.chartScreen = chartScreen;
    }
    
    @Override
    public void showAnalyst(AnalystResponse analystResponse) {
        chartScreen.showAnalyst(analystResponse);
    }
}
