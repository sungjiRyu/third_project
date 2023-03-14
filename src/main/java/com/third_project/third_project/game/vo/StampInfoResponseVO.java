package com.third_project.third_project.game.vo;

import com.third_project.third_project.entity.MemberInfoEntity;
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
    private MemberInfoEntity member;
    private Integer total;
    private Integer ava;
    private Integer use;
}
