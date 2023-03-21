package com.third_project.third_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@Table(name = "game_notice")
public class GameNoticeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gn_seq")      private Long gnSeq;
    @Column(name = "gn_title")    private String gnTitle;
    @Column(name = "gn_content")  private String gnContent;
    @Column(name = "gn_reg_dt")   @ColumnDefault("current_timestamp") private LocalDateTime  gnRegDt;
    @Column(name = "gn_mi_seq")   private Long gnMiSeq;
    @Column(name = "gn_ev_seq")   private Long gnEvSeq;
    @Column(name = "gn_et_seq")   private Long gnEtSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gn_mi_seq", insertable = false, updatable = false)   private MemberInfoEntity member;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gn_ev_seq", insertable = false, updatable = false)   private ExVideoEntity video;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gn_et_seq", referencedColumnName = "", insertable = false, updatable = false)   private ExTypeEntity type;
}
