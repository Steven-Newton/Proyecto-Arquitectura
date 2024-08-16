package com.arquitectura.proyecto.business;

import com.arquitectura.proyecto.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * GLobalExceptionHandler
 * @Author Steven Newto
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) throws Exception {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.OK);
    }
}