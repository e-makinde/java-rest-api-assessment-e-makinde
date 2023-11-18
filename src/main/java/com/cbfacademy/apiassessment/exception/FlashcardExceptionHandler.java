package com.cbfacademy.apiassessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class FlashcardExceptionHandler {
    @ExceptionHandler(value = {FlashcardNotFoundException.class})
    public ResponseEntity<Object> handleFlashcardNotFoundException(FlashcardNotFoundException e) {
        // Create payload containing exception details
        FlashcardException exception = new FlashcardException(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        //Return response entity
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
