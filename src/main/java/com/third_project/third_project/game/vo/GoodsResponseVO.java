package com.third_project.third_project.game.vo;

import com.third_project.third_project.entity.MemberGoodsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsResponseVO {
    private Boolean status;
    private String message;
    private HttpStatus code;
    private List<MemberGoodsResponseVO> list;
}
