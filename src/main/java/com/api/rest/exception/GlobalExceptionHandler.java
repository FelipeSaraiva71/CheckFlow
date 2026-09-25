package com.api.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<String> recursoNaoEncontrado(
            RecursoNaoEncontradoException exception
    )

    {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }



    @ExceptionHandler(ConflitoException.class)
    public ResponseEntity<String> conflito(
            ConflitoException exception
    )

    {
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }


}
