package com.third_project.third_project.game.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class VideoResponseVO {
    private Long miSeq;
    private String filename;
    private String uri;
    private MultipartFile video;
}
