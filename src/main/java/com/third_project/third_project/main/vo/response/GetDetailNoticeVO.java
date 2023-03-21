package com.third_project.third_project.main.vo.response;

import java.time.LocalDateTime;

import com.third_project.third_project.entity.ExTypeEntity;
import com.third_project.third_project.entity.ExVideoEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public interface GetDetailNoticeVO {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "공지사항seq")
    public Long getGnSeq();
    @Schema(description = "공지사항 제목")
    public String getGnTitle();
    @Schema(description = "공지사항 내용")
    public String getGnContent();
    @Schema(description = "공지사항 등록날짜")
    public LocalDateTime  getGnRegDt();

    @Schema(description = "비디오파일")
    @OneToOne @JoinColumn(name = "gn_ev_seq")
    public ExVideoEntity getVideo();

    @Schema(description = "운동 종목")
    @OneToOne @JoinColumn(name = "gn_et_seq")
    public ExTypeEntity getType();
}
