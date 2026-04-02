package br.com.fiap.locatech.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.swing.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<StandardError> objetoNaoEncontradoHandler(ObjetoNaoEncontradoException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = buildStandardError(status, ex.getMessage(), request);

        return ResponseEntity.status(status).body(standardError);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardValidationError> methodNotValidHandler(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<>();
        for (final FieldError errorField : ex.getBindingResult().getFieldErrors()) {
            errors.add(errorField.getField() + ": " + errorField.getDefaultMessage());
        }
        StandardValidationError standardValidationError = new StandardValidationError();
        standardValidationError.setMessages(errors);
        standardValidationError.setPath(request.getRequestURI());
        standardValidationError.setStatus(status.value());

        return ResponseEntity.status(status).body(standardValidationError);

    }



    private StandardError buildStandardError(HttpStatus status, String message, HttpServletRequest request) {
        return new StandardError(
                Instant.now(),
                message,
                status.value(),
                request.getRequestURI()
        );
    }
}
