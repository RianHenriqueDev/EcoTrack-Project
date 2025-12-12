package br.com.rianhenrique.ecotrackproject.utils.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ResponseExceptionHandler {


    @ExceptionHandler(EntityException.class)
    public ResponseEntity<ResponseError> handleEntityException(EntityException ex, HttpServletRequest request) {

        HttpStatus status = ex.getStatus();

        ResponseError exception = new ResponseError(

                Instant.now(),
                status.value(),
                "Erro na Requisição",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }
}
