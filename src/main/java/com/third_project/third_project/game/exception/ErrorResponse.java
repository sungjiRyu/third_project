package com.third_project.third_project.game.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus httpStatus;
    private String errorMessage;

    public static ErrorResponse of(HttpStatus httpStatus, String errorMessage){
        if (errorMessage == null){
            errorMessage = "";
        }
        return new ErrorResponse(httpStatus, errorMessage);
    }
}
