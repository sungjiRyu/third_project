package com.third_project.third_project.main.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class POSTGameNoticeVO {
    @Schema(description = "수정할 공지사항seq", example = "1")  
    private Long  gnSeq;
    @Schema(description = "제목", example = "modifyTitle")  
    private String gnTitle;
    @Schema(description = "내용", example = "modifyContent")  
    private String gnContent;
    @Schema(description = "운동 종목 설정", example = "1")
    private Long gnEtSeq;
    @Schema(description = "등록자", example = "1")
    private Long gnMiSeq;
    @Schema(description = "영상 파일")
    private MultipartFile files;
}
