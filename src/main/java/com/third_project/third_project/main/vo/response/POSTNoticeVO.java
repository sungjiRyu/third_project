package com.third_project.third_project.main.vo.response;

import java.time.LocalDateTime;
import java.util.List;

import com.third_project.third_project.entity.ExVideoEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class POSTNoticeVO {
    @Schema(description = "공지사항 번호", example = "1")  
    private Long gnSeq;
    @Schema(description = "등록자(admin)", example = "1")  
    private Long gnMiSeq;
    @Schema(description = "제목", example = "제목입니다.")  
    private String gnTitle;
    @Schema(description = "내용", example = "내용입니다.")  
    private String gnContent;
    @Schema(description = "등록일" )
    private LocalDateTime gnRegDt;
    // @Schema(description = "첨부파일(영상)", required = false)
    private ExVideoEntity files;
    ResponseMessage responseMessage;
    
}
