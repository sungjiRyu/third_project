package com.third_project.third_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ex_img")
public class ExImgEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eimg_seq")    private Long eimgSeq;
    @Column(name = "eimg_name")    private String eimgName;
    @Column(name = "eimg_url")    private String eimgUrl;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eimg_et_seq", insertable = false, updatable = false)    private ExTypeEntity type;
}
