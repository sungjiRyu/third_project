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
public class GetPersonalExListVO {
    @Schema(description = "운동종목seq", example = "5")
    private Long etSeq;
    @Schema(description = "운동명", example = "걷기")
    private String etName;
    @Schema(description = "운동 상세설명", example = "걸어요")
    private String etDetail;
    @Schema(description = "운동 썸네일 url", example = "")
    private String url;
}
