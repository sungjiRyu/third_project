package com.third_project.third_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "game_notice")
public class GameNoticeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gn_seq")   private Long gnSeq;
    @Column(name = "gn_title")   private String gnTitle;
    @Column(name = "gn_content")   private String gnContent;
    @Column(name = "gn_reg_dt")   private LocalDate gnRegDt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gn_mi_seq", insertable = false, updatable = false)   private MemberInfoEntity member;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gn_ev_seq", insertable = false, updatable = false)   private ExVideoEntity video;
}
