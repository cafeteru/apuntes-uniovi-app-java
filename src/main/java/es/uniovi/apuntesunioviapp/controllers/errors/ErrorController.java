package es.uniovi.apuntesunioviapp.controllers.errors;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * Captura las excepciones en la aplicación
 */
@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Map<String, String[]>> catchIllegalArgumentException(IllegalArgumentException exception) {
        return createResponseEntity(Collections.singletonList(exception.getMessage()).toArray(String[]::new));
    }

    private ResponseEntity<Map<String, String[]>> createResponseEntity(String[] message) {
        Map<String, String[]> errors = new HashMap<>();
        errors.put("error", message);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
