package com.third_project.third_project.member.VO;

import com.third_project.third_project.entity.MemberWeightEntity;
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
public class MemberListResponseVO {
    private List<MemberWeightEntity> list;
    private Boolean status;
    private String message;
    private HttpStatus code;
}
