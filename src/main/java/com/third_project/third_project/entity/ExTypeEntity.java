package com.third_project.third_project.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ex_type")
public class ExTypeEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "운동종목seq")
  @Column(name = "et_seq") private Long etSeq;
  @Schema(description = "운동명")
  @Column(name = "et_name") private String etName;
  @Schema(description = "운동상세설명")
  @Column(name = "et_detail") private String etDetail;
  @Schema(description = "운동타입(1: 다이어트 2: 웨이트 3:게임용)")
  @Column(name = "et_es_seq") private Long etEsSeq;
  @Schema(description = "운동성적타입(1. 시간이 짧을수록 성적이 높음 2. 시간이 길수록 성적이 높음)")
  @Column(name = "et_time_type") private  Integer etTimeType;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "et_gi_seq") private GenInfoEntity gen;
  @ManyToOne
  @JoinColumn(name = "et_level_seq") private ExLevelEntity level;
  @OneToOne(fetch = FetchType.LAZY, mappedBy = "type")
  private ExImgEntity img;
}
