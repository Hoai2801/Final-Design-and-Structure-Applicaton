package org.example.adapter.ui;

import org.example.adapter.controller.ChartController;
import org.example.domain.boundaries.out.OpenChartScreenOutputBoundary;
import org.example.domain.entities.models.AnalystResponse;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ChartScreen extends JFrame implements OpenChartScreenOutputBoundary {

    private Map<String, Integer> invoiceDataVietnamese;
    private Map<String, Integer> invoiceDataForeign;
    private ChartController controller;

    public ChartScreen() {
        setTitle("Monthly Invoice Chart");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ChartPanel chartPanel = new ChartPanel();
        add(chartPanel);
    }

    @Override
    public void openChartScreen() {
        setVisible(true);
        controller.getAnalyst();
    }

    public void showAnalyst(AnalystResponse analystResponse) {
        invoiceDataVietnamese = analystResponse.getInvoiceCountsByMonthVietnamese();
        invoiceDataForeign = analystResponse.getInvoiceCountsByMonthForeign();
        repaint();
    }
    
    public void setController(ChartController controller) {
        this.controller = controller;
    }

    class ChartPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawChart(g);
        }

        private void drawChart(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Chart dimensions
            int width = getWidth();
            int height = getHeight();
            int padding = 50;
            int chartWidth = width - 2 * padding;
            int chartHeight = height - 2 * padding;

            // Draw axes
            g2d.drawLine(padding, height - padding, padding, padding); // Y-axis
            g2d.drawLine(padding, height - padding, width - padding, height - padding); // X-axis

            // Get the maximum value
            int maxValue = Math.max(
                    invoiceDataVietnamese.values().stream().max(Integer::compare).orElse(1),
                    invoiceDataForeign.values().stream().max(Integer::compare).orElse(1)
            );

            // Calculate bar width and spacing
            int numMonths = invoiceDataVietnamese.size();
            int totalBarWidth = chartWidth / numMonths;
            int barWidth = totalBarWidth / 3;
            int spacing = totalBarWidth;

            // Draw bars and labels
            int x = padding + spacing / 2;
            for (String month : invoiceDataVietnamese.keySet()) {
                int vietnameseValue = invoiceDataVietnamese.getOrDefault(month, 0);
                int foreignValue = invoiceDataForeign.getOrDefault(month, 0);

                int vietnameseBarHeight = (int) ((double) vietnameseValue / maxValue * (chartHeight - padding));
                int foreignBarHeight = (int) ((double) foreignValue / maxValue * (chartHeight - padding));

                // Draw Vietnamese invoice bar
                g2d.setColor(Color.BLUE);
                g2d.fillRect(x, height - padding - vietnameseBarHeight, barWidth, vietnameseBarHeight);

                // Draw foreign invoice bar
                g2d.setColor(Color.RED);
                g2d.fillRect(x + barWidth + 5, height - padding - foreignBarHeight, barWidth, foreignBarHeight);

                // Draw values above bars
                g2d.setColor(Color.BLACK);
                g2d.drawString(String.valueOf(vietnameseValue), x + barWidth / 4, height - padding - vietnameseBarHeight - 5);
                g2d.drawString(String.valueOf(foreignValue), x + barWidth + 5 + barWidth / 4, height - padding - foreignBarHeight - 5);

                // Draw month label
                g2d.drawString(month, x + barWidth / 2, height - padding + 15);

                x += spacing;
            }
        }
    }

}
