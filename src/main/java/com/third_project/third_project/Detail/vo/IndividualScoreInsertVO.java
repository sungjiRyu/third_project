package com.third_project.third_project.Detail.vo;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndividualScoreInsertVO {
  private Long isMiSeq;
  private Long isEtSeq;
  private LocalDate isRegDt;
  private LocalTime isTime;
}
