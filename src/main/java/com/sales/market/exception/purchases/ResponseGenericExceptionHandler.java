package com.sales.market.exception.purchases;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseGenericExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { GenericException.class})
    public ResponseEntity<String> handleGenericException (GenericException genericException) {
        return new ResponseEntity<String>(genericException.getMessage(), genericException.getHttpStatus());
    }
}
