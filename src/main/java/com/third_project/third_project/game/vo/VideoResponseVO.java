package com.third_project.third_project.game.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class VideoResponseVO {
    @Schema(description = "회원번호", example = "1")
    private Long miSeq;
    private String filename;
    private String uri;
    private MultipartFile video;
}
