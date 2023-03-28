package com.third_project.third_project.Detail.vo;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.third_project.third_project.entity.ScoreRankListView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreRankListViewResponseVO {
  private List<ScoreRankListView> list;
  private String message;
  private HttpStatus code;
  private Boolean status;
}
