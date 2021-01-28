package com.oddle.app.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class RestError extends RuntimeException {
    private String errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;

}
