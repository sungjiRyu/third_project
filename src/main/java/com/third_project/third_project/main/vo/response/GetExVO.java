package com.third_project.third_project.main.vo.response;

import com.third_project.third_project.entity.ExImgEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// 개인 측정용 운동(8개)
public interface GetExVO {
    @Schema(description = "운동종목seq")
    public Long getEtSeq();
    @Schema(description = "운동명")
    public String getEtName();
    @Schema(description = "운동상세설명")
    public String getEtDetail();
    @Schema(description = "운동이미지")
    public ExImgEntity getImg();
}
