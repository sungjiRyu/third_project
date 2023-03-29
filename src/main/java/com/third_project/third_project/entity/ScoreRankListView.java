package com.third_project.third_project.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "score_rank_list_view")
@Entity
@Immutable
public class ScoreRankListView {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "is_seq") private Long isSeq;
    @Column(name = "is_mi_seq") private Long isMiSeq;
    @Column(name = "is_reg_dt") private LocalDate isRegDt;
    @Column(name = "is_time") private LocalTime isTime;
    @Column(name = "et_name") private String etName;
    @Column(name = "level_type") private String levelType;
    @Column(name = "gi_status") private String giStatus;
    @Column(name = "es_type") private String esType;
}
