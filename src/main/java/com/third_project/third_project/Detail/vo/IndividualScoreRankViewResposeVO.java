package com.third_project.third_project.Detail.vo;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.third_project.third_project.entity.IndividualScoreRankView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndividualScoreRankViewResposeVO {
  private List<IndividualScoreRankView> list;
  private HttpStatus code;
  private String message;
  private Boolean status;
}
