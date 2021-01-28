package com.oddle.app.api.endpoint;

import com.oddle.app.common.RestError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class WeatherGatewayExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestError handleCustomException(Exception ce) {
        throw new RestError(HttpStatus.NOT_FOUND.name(), "city not found", HttpStatus.NOT_FOUND);
    }

}
