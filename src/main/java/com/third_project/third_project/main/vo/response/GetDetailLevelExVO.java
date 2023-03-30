package com.third_project.third_project.main.vo.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// 레벨별 운동 상세조회
// 운동 상세이미지url, 운동종목seq, 상세설명
public class GetDetailLevelExVO {
    @Schema(description = "운동종목seq" , example = "19")
    private Long   etSeq;
    @Schema(description = "운동명" , example = "달리기LV1")
    private String   etName;
    @Schema(description = "운동시간" , example = "10분")
    private String etDetail;
    @Schema(description = "상세 이미지 url" , example = "detail_level_run")
    private String url;
    @Schema(description = "운동 상세 설명" , example = "줄넘기")
    private String etExplain;

}
