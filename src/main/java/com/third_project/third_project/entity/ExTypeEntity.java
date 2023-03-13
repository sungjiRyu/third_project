package com.third_project.third_project.entity;

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
@Entity
@Table(name = "ex_type")
public class ExTypeEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "et_seq") private Long etSeq;
  @Column(name = "et_name") private String etName;
  @Column(name = "et_detail") private String etDetail;
  @Column(name = "et_es_seq") private Long etEsSeq;
  @Column(name = "et_time_type") private  Long etTimeType;
  @Column(name = "et_eimg_seq") private Long etEimgSeq;
  @Column(name = "et_gi_seq") private Long etGiSeq;
  @Column(name = "et_level_seq") private Long etLevelSeq;
  
}
