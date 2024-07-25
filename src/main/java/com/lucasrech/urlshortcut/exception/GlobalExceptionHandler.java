package com.lucasrech.urlshortcut.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlNotFoundedException.class)
    public ResponseEntity<ExceptionDTO> UrlNotFounded(UrlNotFoundedException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDTO(exception.getMessage(), "404"));
    }

    @ExceptionHandler(UrlAlreadyExistsException.class)
    public ResponseEntity<ExceptionDTO> UrlAlreadyExists(UrlAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionDTO(exception.getMessage(), "409"));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDTO> Unexpected(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionDTO(exception.getMessage(), "500"));
    }

}
