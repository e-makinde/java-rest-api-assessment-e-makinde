package com.cbfacademy.apiassessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = {FlashcardNotFoundException.class})
    public ResponseEntity<Object> handleFlashcardNotFoundException(FlashcardNotFoundException e) {
        // Create payload containing exception details
        ApplicationException exception = new ApplicationException(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        //Return response entity
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value =  {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        ApplicationException exception = new ApplicationException(errorMap.toString(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());

        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ApplicationException exception = new ApplicationException(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
