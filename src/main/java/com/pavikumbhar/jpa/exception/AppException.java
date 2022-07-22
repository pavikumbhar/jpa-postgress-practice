package com.pavikumbhar.jpa.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class AppException extends RuntimeException {

    private HttpStatus status;
    private String message;
    public AppException(String message) {
        super(message);
    }

    public AppException(String message,HttpStatus status) {
        super(message);
        this.status=status;
    }
}
