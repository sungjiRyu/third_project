package com.third_project.third_project.entity;

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
  @Column(name = "et_seq") private Long etSeq;
  @Column(name = "et_name") private String etName;
  @Column(name = "et_detail") private String etDetail;
  @Column(name = "et_es_seq") private Long etEsSeq;
  @Column(name = "et_time_type") private  Long etTimeType;
  @ManyToOne
  @JoinColumn(name = "et_gi_seq") private GenInfoEntity gen;
  @ManyToOne
  @JoinColumn(name = "et_level_seq") private ExLevelEntity level;
  @OneToOne(fetch = FetchType.LAZY, mappedBy = "type")
  private ExImgEntity img;
}
