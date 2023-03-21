package com.third_project.third_project.main.vo.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class GetVideoVO {
    @Schema(description = "파일명")
    public String evName;
    @Schema(description = "파일uri")
    public String evUrl;
}
