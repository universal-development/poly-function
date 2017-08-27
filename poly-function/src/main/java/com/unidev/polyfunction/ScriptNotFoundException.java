package com.unidev.polyfunction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Script not found exception
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Not found")
public class ScriptNotFoundException extends RuntimeException {

    public ScriptNotFoundException() {
    }

    public ScriptNotFoundException(String message) {
        super(message);
    }

    public ScriptNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScriptNotFoundException(Throwable cause) {
        super(cause);
    }
}
