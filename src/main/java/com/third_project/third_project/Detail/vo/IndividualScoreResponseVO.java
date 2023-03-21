package com.third_project.third_project.Detail.vo;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndividualScoreResponseVO {
  private HttpStatus code;
  private Boolean status;
  private String message;
}
