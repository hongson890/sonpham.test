package com.oddle.app.gateway;

import com.oddle.app.common.RestError;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public abstract class ExceptionHandler {

    public void handleHttpException(HttpClientErrorException e) throws RestError {
        HttpStatus status = e.getStatusCode();
        switch (status) {
            case NOT_FOUND:
                throw new RestError(HttpStatus.NOT_FOUND.name(), "city not found", e.getStatusCode());
            case FORBIDDEN:
                throw new RestError(HttpStatus.FORBIDDEN.name(), "not permit", e.getStatusCode());
            default:
                throw new RestError(HttpStatus.INTERNAL_SERVER_ERROR.name(), "internal server error", e.getStatusCode());
        }
    }
}
