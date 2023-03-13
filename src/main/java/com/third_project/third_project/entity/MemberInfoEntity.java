package com.third_project.third_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="member_info")
public class MemberInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mi_seq")          private Long   miSeq;
    @Column(name="mi_id")           private String miId;
    @Column(name="mi_pwd")          private String miPwd;
    @Column(name="mi_tall")         private Integer miTall;
    @Column(name="mi_weight")       private Integer miWeight;
    @Column(name="mi_nickname")     private String miNickname;
    @Column(name="mi_role")         private String miRole;
    @Column(name="mi_gi_seq")       private Long miGiSeq;
    @Column(name="mi_class_seq")    private Long miClassSeq;
    @Column(name="mi_es_seq")       private Long miEsSeq;
    @Column(name="mi_mimg_seq")     private Long miMimgSeq;
}
