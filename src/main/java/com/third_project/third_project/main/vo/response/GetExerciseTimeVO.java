package com.third_project.third_project.main.vo.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @Builder
public interface GetExerciseTimeVO {
    @Schema(description = "운동종목번호", example = "5(걷기), 6(사이클링), 7(요가), 8(댄스) ,9(코어트레이닝), 10(필라테스), 11(수영), 12(하이킹)")
    public Long getIsEtSeq();
    @Schema(description = "설정한 기간동안 운동종목별 총 운동시간", example = "00:10:00")
    public String getIsTime();
}
