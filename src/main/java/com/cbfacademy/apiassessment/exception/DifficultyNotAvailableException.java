package com.cbfacademy.apiassessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DifficultyNotAvailableException extends RuntimeException {
    public DifficultyNotAvailableException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return String.join(" - ", super.getMessage());
    }
}
