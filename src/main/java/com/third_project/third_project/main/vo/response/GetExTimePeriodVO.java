package com.third_project.third_project.main.vo.response;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @Builder
public interface GetExTimePeriodVO {
    // 운동 카테고리 번호
    public Long getIsEtSeq();
    // 운동시간
    public LocalTime getIsTime();
}
