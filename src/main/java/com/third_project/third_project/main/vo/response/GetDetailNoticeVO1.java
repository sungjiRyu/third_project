package com.third_project.third_project.main.vo.response;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// 공지사항 상세정보 보기
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetDetailNoticeVO1 {

@Schema(description = "공지사항 제목", example = "이번주 미션 : 달리기")
private Long gnSeq;
@Schema(description = "공지사항 제목", example = "이번주 미션 : 달리기")
private String gnTitle;
@Schema(description = "공지사항 내용", example = "열심히 뛰세요")
private String gnContent;
@Schema(description = "공지사항 등록일", example = "2023-03-17T21:46:17.348")
private LocalDateTime gnRegDt;
@Schema(description = "공지사항 영상파일 url", example = "notice_1679392254613.mp4")
private String url;
@Schema(description = "공지사항으로 등록한 운동 종류 번호 ", example = "5")
private Long etSeq;
}
