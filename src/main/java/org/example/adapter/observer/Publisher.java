package org.example.adapter.observer;

import org.example.adapter.ui.model.InvoicesModel;
import org.example.domain.entities.models.ListInvoiceResponseModel;
import org.example.domain.entities.models.ResponseModel;

import java.util.List;

public class Publisher {
    private final List<Observer> observers = new java.util.ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyMessageObservers(ResponseModel responseModel) {
        for (Observer observer : observers) {
            observer.updateMessage(responseModel);
        }
    }
    
    public void generateReport(ListInvoiceResponseModel invoices) {
        for (Observer observer : observers) {
            observer.generateReport(invoices);
        }
    }

    public void notifyErrorObservers(ResponseModel error) {
        for (Observer observer : observers) {
            observer.updateError(error);
        }
    }
}
