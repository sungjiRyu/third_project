package com.third_project.third_project.Detail.vo;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.third_project.third_project.entity.WeekScore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeekScoreResponseVO {
  private List<WeekScore> list;
  private String message;
  private HttpStatus code;
  private Boolean status;
}
