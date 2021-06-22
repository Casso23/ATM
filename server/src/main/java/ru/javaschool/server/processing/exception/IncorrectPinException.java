package ru.javaschool.server.processing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IncorrectPinException extends RuntimeException {
    public IncorrectPinException() {
    }

    public IncorrectPinException(String message) {
        super(message);
    }

    public IncorrectPinException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPinException(Throwable cause) {
        super(cause);
    }

    public IncorrectPinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

