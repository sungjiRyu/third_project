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
public class GameScoreInsertVO {
    @Schema(description = "회원 운동 기록", example = "00:09:00")
    private LocalTime score;
    @Schema(description = "회원 번호", example = "1")
    private Long miSeq;
    @Schema(description = "운동 종류 번호", example = "1")
    private Long etSeq;
}
