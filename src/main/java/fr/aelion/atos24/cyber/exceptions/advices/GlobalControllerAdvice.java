package fr.aelion.atos24.cyber.exceptions.advices;

import fr.aelion.atos24.cyber.models.ApiError;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Level;

@Log
@ControllerAdvice(basePackages = "fr.aelion.atos24.cyber.controllers")
public class GlobalControllerAdvice {

    @ExceptionHandler({
            jakarta.validation.ConstraintViolationException.class,
            org.springframework.dao.DataIntegrityViolationException.class
    })
    public ResponseEntity<ApiError> dataValidationError(Exception e) {
        // TODO: make logging even more generic ? ...
        log.log(Level.WARNING, e.getMessage());
        // TODO: make ApiError code more specific and associated to a msg (like a single object with code + msg)?
        // TODO: make ApiError msg more explicit according to context or exception message, and use i18n compatible logic?
        return new ResponseEntity<>(
                new ApiError(HttpStatus.BAD_REQUEST.value(), "Erreur de validation", e),
                HttpStatus.BAD_REQUEST);
    }
}
