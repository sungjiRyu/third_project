package com.third_project.third_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "game_score")
public class GameScoreEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gs_seq")   private Long gsSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gs_mi_seq")   private MemberInfoEntity member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gs_et_seq")   private ExTypeEntity exType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "gs_reg_dt")   private LocalDate gsRegDt;
    @DateTimeFormat(pattern = "HH:mm:ss.SSS")
    @Column(name = "gs_time")   private LocalTime gsTime;

    @Column(name = "gs_percent") @ColumnDefault("0") private Double gsPercent;
}
