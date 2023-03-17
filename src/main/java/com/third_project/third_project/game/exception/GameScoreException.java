package com.third_project.third_project.game.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class GameScoreException extends RuntimeException{
    private ErrorResponse errorResponse;

    @Override
    public String getMessage(){
        return String.format("%d  message: %s", errorResponse.getHttpStatus().value() ,errorResponse.getErrorMessage());
    }

    public HttpStatus getHttpStatus(){
        return this.errorResponse.getHttpStatus();
    }
}
