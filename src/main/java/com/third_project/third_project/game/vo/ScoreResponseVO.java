package com.third_project.third_project.game.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ScoreResponseVO {
    private Boolean status;
    private String message;
    @JsonIgnore
    private HttpStatus code;
}
