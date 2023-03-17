package com.third_project.third_project.game.vo;

import com.third_project.third_project.entity.MemberInfoEntity;
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
public class StampInfoResponseVO {
    private Boolean status;
    private String message;
    private HttpStatus code;
    @Schema(description = "스템프 총 칸수 ", example = "20")
    private Integer total;
    @Schema(description = "사용 가능한 횟수", example = "5")
    private Integer available;
    @Schema(description = "사용한 횟수", example = "10")
    private Integer use;
}
