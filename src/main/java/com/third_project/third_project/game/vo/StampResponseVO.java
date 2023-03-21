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
public class StampResponseVO {
    private Boolean status;
    private String message;
    private HttpStatus code;
    @Schema(description = "회원 보유 상품 정보", example = "gs편의점 3000원 이용권")
    private String goods;
}
