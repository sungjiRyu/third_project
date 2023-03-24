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
    @Schema(description = "운동종목" , example = "19")
    private Long   etSeq;
    @Schema(description = "운동상세설명" , example = "달리기")
    private String etDetail;
    @Schema(description = "상세 이미지 url" , example = "detail_level_run")
    private String url;

}
