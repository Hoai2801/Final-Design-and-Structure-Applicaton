package org.example.domain.entities.dtos;

public class NotifyResponse {
    private boolean success;
    private String message;
    
    public NotifyResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public String getMessage() {
        return message;
    }
}
