package com.third_project.third_project.main.vo;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;


// 오류처리 클래스
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
