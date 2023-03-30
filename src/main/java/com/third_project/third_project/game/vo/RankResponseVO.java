package com.third_project.third_project.game.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class RankResponseVO {
    @Schema(description = "상태", example = "true")
    private Boolean status;
    @Schema(description = "메세지", example = "인증에 성공했습니다")
    private String message;
    @JsonIgnore
    @Schema(description = "HTTP 상태 코드", example = "HttpStatus.BAD_REQUEST")
    private HttpStatus code;
    private List<RankListResponseVO> list;
}
