package org.example.domain.boundaries.in;

public interface OpenUpdateScreenInputBoundary {
    void execute();

    void execute(int customerId, String type);
}
