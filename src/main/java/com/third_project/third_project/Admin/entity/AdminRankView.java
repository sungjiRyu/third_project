package com.third_project.third_project.Admin.entity;


import java.time.LocalDate;
import java.time.LocalTime;

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
@Table(name = "admin_rank_view")
@Entity
public class AdminRankView {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "gs_seq") private Long gsSeq;
  @Column(name = "mi_nickname") private String miNickName;
  @Column(name = "gs_et_seq") private Long gsEtSeq;
  @Column(name = "et_name") private String etName;
  @Column(name = "gs_reg_dt") private LocalDate gsRegDt;
  @Column(name = "gs_time") private LocalTime gsTime;
  @Column(name = "gs_percent") private Double percent;
}
