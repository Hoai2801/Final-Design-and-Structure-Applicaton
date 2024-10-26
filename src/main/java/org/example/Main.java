package org.example;

import org.example.adapter.controller.InvoiceController;
import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.VietnameseRepository;
import org.example.adapter.ui.Presenter;
import org.example.adapter.ui.swing.SwingUI;
import org.example.domain.interactors.Interactor;

public class Main {
    public static void main(String[] args) {
        Presenter presenter = new Presenter();
        SwingUI swingUI = new SwingUI();
        presenter.addObserver(swingUI);
        Interactor interactor = new Interactor(new VietnameseRepository(), new ForeignRepository(), presenter);
        InvoiceController invoiceController = new InvoiceController(interactor);
        swingUI.setInvoiceController(invoiceController);
        swingUI.initComponents();
    }
}