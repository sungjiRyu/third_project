package com.third_project.third_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name="triple_raking_view")
public class TripleRankingView {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="mi_seq") private Long miSeq;
    @Column (name="et_seq") private Long etSeq;
    @Column (name="ranking") private Integer rank;
    @Column (name="mi_nickname") private String miNickName;
    @Column (name="mi_classnum") private String miClassNum;
    @Column (name="gs_time") private LocalTime gsTime;
    @Column (name="et_time_type") private Integer etTimeType;
    @Column (name="mimg_url") private String mingUrl;
    @Column (name="gs_reg_dt") private Date gsRegDt;
}
