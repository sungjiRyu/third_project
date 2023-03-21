package com.third_project.third_project.Detail.vo;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class updateIndividualScoreInsertVO {
  private Long upateIsEtSeq;
  private LocalTime upateIsTime;
}
