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
@Table(name="member_img")
public class MemberImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mimg_seq")
    private Long mimgSeq;
    @Column(name = "mimg_name")
    private String mimgName;
    @Column(name = "mimg_url")
    private String mimgUrl;
}

