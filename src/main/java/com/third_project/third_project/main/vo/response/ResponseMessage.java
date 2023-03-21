package com.third_project.third_project.main.vo.response;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseMessage {
    @Schema(description = "상태값", example = "false")
    private Boolean status;
    @Schema(description = "HTTP상태코드", example = "BadRequest")
    private HttpStatus code;
    @Schema(description = "메시지", example = "실패했습니다.")
    private String message;

    public ResponseMessage responseMessage(Boolean Status, HttpStatus code, String message){
        return responseMessage(Status, code, message);
    }
}
