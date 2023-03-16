package com.third_project.third_project.main.vo.response;

import java.time.LocalDateTime;

import com.third_project.third_project.entity.ExTypeEntity;
import com.third_project.third_project.entity.ExVideoEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetDetailNoticeVO {
@Schema(description = "공지사항seq")
private Long gnSeq;
@Schema(description = "공지사항 제목")
private String gnTitle;
@Schema(description = "공지사항 내용")
private String gnContent;
@Schema(description = "공지사항 등록날짜")
private LocalDateTime  gnRegDt;
@Schema(description = "비디오파일")
private ExVideoEntity video;
@Schema(description = "운동 종목")
private ExTypeEntity type;
}
