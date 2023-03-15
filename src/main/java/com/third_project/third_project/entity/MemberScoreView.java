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
@Table(name="member_score_view")
public class MemberScoreView {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="mi_seq") private Long miSeq;
    @Column (name="mi_nickname") private String miNickName;
    @Column (name="mi_class") private Integer miClass;
    @Column (name="gs_time") private LocalTime gsTime;
    @Column (name="et_time_type") private Integer etTimeType;
    @Column (name="mimg_url") private String mingUrl;
    @Column (name="gs_reg_dt") private Date gsRegDt;
}
