package com.third_project.third_project.main.vo.request;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UPDATEGameNoticeVO {
    @Schema(description = "공지사항seq", example = "1")  
    private Long gnSeq;
    @Schema(description = "제목", example = "testTitle")  
    private String gnTitle;
    @Schema(description = "내용", example = "testContent")  
    private String gnContent;
    @Schema(description = "운동 종목 설정", example = "1")
    private Long gnEtSeq;
    @Schema(description = "영상 파일")
    private MultipartFile files;
}
