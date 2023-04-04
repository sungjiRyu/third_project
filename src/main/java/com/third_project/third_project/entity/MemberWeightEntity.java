package com.third_project.third_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member_weight")
@DynamicInsert
public class MemberWeightEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mw_seq")                private Long mwSeq;
    @ManyToOne
    @JoinColumn(name = "mw_mi_seq")
    @JsonIgnore                             private MemberInfoEntity member;
    @Column(name = "mw_reg_dt")
    @ColumnDefault("CURRENT_TIMESTAMP")     private LocalDate mwRegDt;
    @Column(name = "mw_weight")             private Integer mwWeight;
}
