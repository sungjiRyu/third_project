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
@Table(name = "certification_video")
public class CertificationVideoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cv_seq")    private Long cvSeq;
    @Column(name = "cv_mi_seq")    private Long cvMiSeq;
    @Column(name = "cv_url")    private String cvUrl;
    @Column(name = "cv_name")    private String cvName;
}
