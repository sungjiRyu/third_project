package com.third_project.third_project.Admin.vo;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeResponseVO {
    private String message;
    private HttpStatus code;
    private Boolean status;
}
