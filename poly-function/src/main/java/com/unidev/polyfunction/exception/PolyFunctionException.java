package com.unidev.polyfunction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Script not found exception
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Service error")
public class PolyFunctionException extends RuntimeException {

    public PolyFunctionException() {
    }

    public PolyFunctionException(String message) {
        super(message);
    }

    public PolyFunctionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PolyFunctionException(Throwable cause) {
        super(cause);
    }
}
