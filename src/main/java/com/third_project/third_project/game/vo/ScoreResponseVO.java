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
public class ScoreResponseVO {
    private Boolean status;
    private String message;
    @JsonIgnore
    @Schema(description = "HTTP 상태 코드", example = "HttpStatus.BAD_REQUEST")
    private HttpStatus code;
    @Schema(description = "회원 닉네임", example = "침착맨")
    private String nickname;
    @Schema(description = "회원 등수", example = "3")
    private Integer rank;
    @Schema(description = "회원 운동 기록", example = "00:09:00")
    private LocalTime score;
    @Schema(description = "회원 이미지", example = "member.jpg")
    private String url;
    @Schema(description = "회원 반", example = "3학년 2반")
    private String ban;
}
