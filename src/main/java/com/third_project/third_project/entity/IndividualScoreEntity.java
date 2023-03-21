package com.third_project.third_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="individual_score")
public class IndividualScoreEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "개인 운동 seq")
    @Column(name="is_seq")     private Long isSeq;
    @Schema(description = "학생 seq")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="is_mi_seq")  private MemberInfoEntity member;
    @Schema(description = "운동 타입 seq")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="is_et_seq")  private ExTypeEntity exType;
    @Schema(description = "개인 운동 등록일")
    @Column(name="is_reg_dt")  private LocalDate isRegDt;
    @Schema(description = "개인 운동 기록")
    @Column(name="is_time")    private LocalTime isTime;
}


