package com.third_project.third_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "game_score")
public class GameScoreEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gs_seq")   private Long gsSeq;
    @Column(name = "gs_mi_seq")   private Long gsMiSeq;
    @Column(name = "gs_et_seq")   private Long gsEtSeq;
    @Column(name = "gs_reg_dt")   private LocalDate gsRegDt;
    @Column(name = "gs_time")   private LocalTime gsTime;
}
