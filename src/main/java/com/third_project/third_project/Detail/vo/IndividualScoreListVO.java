package com.third_project.third_project.Detail.vo;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.third_project.third_project.entity.IndividualScoreEntity;
import com.third_project.third_project.entity.ScoreListView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndividualScoreListVO {
  private List<ScoreListView> list;
  private String message;
  private HttpStatus code;
  private Boolean status;
}
