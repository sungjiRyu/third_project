package com.third_project.third_project.main.vo.exceptionHendler;

import org.springframework.http.HttpStatus;

import com.third_project.third_project.main.vo.ErrorResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExceptionHendler extends RuntimeException{

      private ErrorResponse errorResponse;


       @Override
       public String getMessage(){
        return String.format("%d  message: %s", errorResponse.getHttpStatus().value() ,errorResponse.getErrorMessage());
      }

      public HttpStatus getHttpStatus(){
        return this.errorResponse.getHttpStatus();
      }
  
}

