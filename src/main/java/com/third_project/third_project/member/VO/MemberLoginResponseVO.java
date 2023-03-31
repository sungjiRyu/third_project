package com.third_project.third_project.member.VO;

import com.third_project.third_project.security.vo.TokenVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import com.third_project.third_project.security.vo.TokenVO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLoginResponseVO {
    private Boolean status;
    private String message;
    private HttpStatus code;
    private Long miSeq;
    private TokenVO token;
}
