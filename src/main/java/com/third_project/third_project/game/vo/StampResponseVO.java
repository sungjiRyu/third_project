package com.third_project.third_project.game.vo;

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
    private String goods;
}
