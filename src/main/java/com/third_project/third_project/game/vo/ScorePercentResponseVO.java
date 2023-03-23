package com.third_project.third_project.game.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScorePercentResponseVO {
    @Schema(description = "상태 값", example = "true")
    private Boolean status;
    @Schema(description = "출력 메세지", example = "정보가 조회되었습니다!")
    private String message;
    @Schema(description = "HttpStatus 상태", example = "OK")
    private HttpStatus code;
    @Schema(description = "상위 % 정보", example = "20.22")
    private Double percent;
}
