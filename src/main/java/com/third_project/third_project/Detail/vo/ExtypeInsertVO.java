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
public class ExtypeInsertVO {
  private String etName;
  private String etDetail;
  private Long etEsSeq;
  private Integer etTimeType;
  private Long gen;
  private Long level;
  private Long eimg;

}
