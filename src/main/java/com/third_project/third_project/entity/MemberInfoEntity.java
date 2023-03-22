package com.third_project.third_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@DynamicInsert
@Table(name="member_info")
public class MemberInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mi_seq")          private Long   miSeq;
    @Column(name="mi_id")           private String miId;
    @Column(name="mi_pwd")          private String miPwd;
    @Column(name="mi_tall")         private Integer miTall;
    @Column(name="mi_weight")       private Integer miWeight;
    @Column(name="mi_nickname")     private String miNickname;
    @Column(name="mi_role")
    @ColumnDefault("user") private String miRole;
    @Column(name="mi_class")
    @ColumnDefault("반 선택") private String miClassNum;
    @OneToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name="mi_gi_seq")
    @ColumnDefault("1") private GenInfoEntity gen;
    @OneToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name="mi_es_seq")
    @ColumnDefault("1") private ExStatusEntity exStatus;
    @OneToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name="mi_mimg_seq")     private MemberImgEntity mimg;
}
