package com.third_project.third_project.main.vo.response;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;

// 공지사항 제목 리스트 출력VO

// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
public interface GetNoticeVO {
    @Schema(description = "공지사항 seq")
    public Long getGnSeq();
    @Schema(description = "제목")
    public String getGnTitle();
    @Schema(description = "등록일")
    public LocalDateTime  getGnRegDt();
    @Schema(description = "운동종목")
    public Long getGnEtSeq();
}
