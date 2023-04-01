package com.third_project.third_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sum_score_date_view")
public class SumScoreDateViewEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "is_seq")       private Long isSeq;
    @Column(name = "mi_seq")       private Long miSeq;
    @Column(name = "mi_nickname")       private String miNickname;
    @Column(name = "da")       private LocalDate da;
    @Column(name = "total")       private Integer total;
}
