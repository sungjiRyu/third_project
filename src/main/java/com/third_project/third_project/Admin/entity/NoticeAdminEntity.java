package com.third_project.third_project.Admin.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

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
@DynamicInsert
@Table(name = "game_notice")
@Entity
public class NoticeAdminEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "gn_seq") private Long gnSeq;
  @Column(name = "gn_title") private String gnTitle;
  @Column(name = "gn_content") private String gnContent;
  @Column(name = "gn_reg_dt")  @ColumnDefault("current_timestamp") private LocalDate gnRegDt;
  @Column(name = "gn_mi_seq") private Long gnMiSeq;
  @Column(name = "gn_ev_seq") private Long gnEvSeq;
  @Column(name = "gn_et_seq") private Long gnEtSeq;
  
}
