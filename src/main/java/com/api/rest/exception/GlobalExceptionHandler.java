package com.api.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> erros = new LinkedHashMap<>();
        Map<String, Integer> prioridades = new LinkedHashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {

                    String campo = error.getField();
                    int prioridade = prioridade(error.getCode());

                    if (!prioridades.containsKey(campo)
                            || prioridade < prioridades.get(campo)) {

                        prioridades.put(campo, prioridade);
                        erros.put(campo, error.getDefaultMessage());
                    }
                });

        return ResponseEntity
                .badRequest()
                .body(erros);
    }

    private int prioridade(String codigo) {

        return switch (codigo) {
            case "NotBlank" -> 1;
            case "NotNull" -> 2;
            case "Pattern" -> 3;
            case "Size" -> 4;
            case "Email" -> 5;
            default -> 99;
        };
    }


}
