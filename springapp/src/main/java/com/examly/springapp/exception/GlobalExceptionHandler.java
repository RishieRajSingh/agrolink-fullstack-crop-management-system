package com.examly.springapp.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;


/**
 * @author Team AgroVisionaries
 * Global exception handler for handling application-specific exceptions.
 * 
 * Annotated with `@ControllerAdvice` to act as a Spring-managed component for exception handling.
 * Contains methods annotated with `@ExceptionHandler` to define custom error responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles `DuplicateAgroChemicalException` and returns an appropriate response.
     *
     * @param e the exception object.
     * @return a `ResponseEntity` with a 409 status code and the exception message.
     */
    @ExceptionHandler(DuplicateAgroChemicalException.class)
    public ResponseEntity<String> duplicateAgroChemicalExceptionHandler(DuplicateAgroChemicalException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<String> alreadyExistsExceptionHandler(EntityAlreadyExistsException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(CropNotFoundException.class)
    public ResponseEntity<String> cropNotFoundExceptionHandler(CropNotFoundException e){
        return ResponseEntity.status(204).body(e.getMessage());
    }
    @ExceptionHandler(RequestNotFoundException.class)
    public ResponseEntity<String> requestNotFoundExceptionHandler(RequestNotFoundException e){
        return ResponseEntity.status(204).body(e.getMessage());
    }
    @ExceptionHandler(AgroChemicalNotFoundException.class)
    public ResponseEntity<String> agroChemicalNotFoundExceptionHandler(AgroChemicalNotFoundException e){
        return ResponseEntity.status(204).body(e.getMessage());
    }
    @ExceptionHandler(FeedbackNotFoundException.class)
    public ResponseEntity<String> feedbackNotFoundExceptionHandler(FeedbackNotFoundException e){
        return ResponseEntity.status(204).body(e.getMessage());
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundException(EntityNotFoundException e){
        return ResponseEntity.status(204).body(e.getMessage());
    }

    /**
     * Handles `AgroChemical CropAlreadyExistsException` and returns an appropriate response.
     *
     * @param e the exception object.
     * @return a `ResponseEntity` with a 409 status code and the exception message.
     */
    @ExceptionHandler(CropAlreadyExistsException.class)
    public ResponseEntity<String> cropAlreadyExistsException(CropAlreadyExistsException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }
}
