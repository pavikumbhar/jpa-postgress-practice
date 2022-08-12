package com.pavikumbhar.jpa.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class AppException extends RuntimeException {
    @SuppressWarnings("java:S1165")
    private HttpStatus status;
    @SuppressWarnings("java:S1165")
    private String message;
    public AppException(String message) {
        super(message);
        this.message=message;
    }

    public AppException(String message,HttpStatus status) {
        super(message);
        this.message=message;
        this.status=status;
    }
}
