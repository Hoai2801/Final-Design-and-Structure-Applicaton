package org.example.adapter.ui.swing;

import org.example.domain.boundaries.out.OutputAnalystBoundary;

import javax.swing.*;
import java.awt.*;

public class AnalystUI extends JFrame implements OutputAnalystBoundary {
    JLabel totalInvoiceOfVietnamese = new JLabel("Total Invoice of Vietnamese:");
    JLabel totalInvoiceOfForeign = new JLabel("Total Invoice of Foreign:");
    JLabel averageTotalOfForeign = new JLabel("Average Total of Foreign:");

    public AnalystUI() {
        initComponents();
    }

    public void initComponents() {
        totalInvoiceOfVietnamese.setFont(new Font("Arial", Font.BOLD, 14));
        totalInvoiceOfForeign.setFont(new Font("Arial", Font.BOLD, 14));
        averageTotalOfForeign.setFont(new Font("Arial", Font.BOLD, 14));

        totalInvoiceOfVietnamese.setForeground(Color.BLUE);
        totalInvoiceOfForeign.setForeground(Color.BLUE);
        averageTotalOfForeign.setForeground(Color.BLUE);

        JPanel contentPane = new JPanel();
        GroupLayout layout = new GroupLayout(contentPane);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(totalInvoiceOfVietnamese)
                .addComponent(totalInvoiceOfForeign)
                .addComponent(averageTotalOfForeign));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(totalInvoiceOfVietnamese)
                .addComponent(totalInvoiceOfForeign)
                .addComponent(averageTotalOfForeign));

        contentPane.setLayout(layout);
        this.setContentPane(contentPane);

        this.setTitle("Analyst UI");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void renderData(double totalInvoiceOfVietnamese, double totalInvoiceOfForeign, double averageTotalOfForeign) {
        this.totalInvoiceOfVietnamese.setText("Total Invoice of Vietnamese: " + totalInvoiceOfVietnamese);
        this.totalInvoiceOfForeign.setText("Total Invoice of Foreign: " + totalInvoiceOfForeign);
        this.averageTotalOfForeign.setText("Average Total of Foreign: " + averageTotalOfForeign);
        this.setVisible(true);
    }

    public void setTotalInvoiceOfVietnamese(double totalInvoiceOfVietnamese) {
        this.totalInvoiceOfVietnamese.setText("Total Invoice of Vietnamese: " + totalInvoiceOfVietnamese);
    }

    public void setTotalInvoiceOfForeign(double totalInvoiceOfForeign) {
        this.totalInvoiceOfForeign.setText("Total Invoice of Foreign: " + totalInvoiceOfForeign);
    }

    public void setAverageTotalOfForeign(double averageTotalOfForeign) {
        this.averageTotalOfForeign.setText("Average Total of Foreign: " + averageTotalOfForeign);
    }
}
