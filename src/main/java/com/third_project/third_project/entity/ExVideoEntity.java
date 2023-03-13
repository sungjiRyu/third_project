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
@Table(name = "ex_video")
public class ExVideoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ev_seq")    private Long evSeq;
    @Column(name = "ev_name")    private String evName;
    @Column(name = "ev_url")    private String evUrl;
    @Column(name = "ev_et_seq")    private Long evEtSeq;
}
