package org.example.domain.entities.dtos;

public class ValidResult {
    private boolean isValid;
    private String error;

    public ValidResult(boolean isValid, String message) {
        this.isValid = isValid;
        this.error = message;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getError() {
        return error;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public void setError(String error) {
        this.error = error;
    }

}
