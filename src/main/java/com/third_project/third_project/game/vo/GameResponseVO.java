package com.third_project.third_project.game.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalTime;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class GameResponseVO {
    @Schema(description = "상태", example = "true")
    private Boolean status;
    @Schema(description = "메세지", example = "인증에 성공했습니다")
    private String message;
    @JsonIgnore
    @Schema(description = "HTTP 상태 코드", example = "HttpStatus.BAD_REQUEST")
    private HttpStatus code;
//    @Schema(description = "회원 운동 기록", example = "00:09:00")
//    private LocalTime score;
//    @Schema(description = "회원 번호", example = "1")
//    private Long miSeq;
//    @Schema(description = "운동 종류 번호", example = "1")
//    private Long etSeq;
}
