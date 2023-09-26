package com.patient.hub.patienthub.exception;

import com.patient.hub.patienthub.dto.ErrorStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PatientHubExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorStatus> handle(MethodArgumentNotValidException ex) {
        ErrorStatus errorStatus = new ErrorStatus();
        errorStatus.setStatusCode(ex.getStatusCode().value());
        ex.getFieldErrors().forEach(fieldError -> {
            errorStatus.getErrorMessages().add(fieldError.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorStatus);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorStatus> handle(NotFoundException ex) {
        ErrorStatus errorStatus = new ErrorStatus();
        errorStatus.setStatusCode(404);
        errorStatus.getErrorMessages().add(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorStatus);
    }
}
