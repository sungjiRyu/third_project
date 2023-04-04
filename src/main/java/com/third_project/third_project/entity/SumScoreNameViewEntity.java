package com.third_project.third_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sum_score_name_view")
public class SumScoreNameViewEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "is_seq")    private Long isSeq;
    @Column(name = "mi_seq")     private Long miSeq;
    @Column(name = "mi_nickname")     private String miNickname;
    @Column(name = "et_name")     private String etName;
    @Column(name = "total")     private Integer total;
}
