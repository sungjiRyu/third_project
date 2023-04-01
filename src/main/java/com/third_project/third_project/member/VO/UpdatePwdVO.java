package com.third_project.third_project.member.VO;

import com.third_project.third_project.entity.MemberImgEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePwdVO {
    private String pwd;
    private String confirmpwd;
    private MemberImgEntity ming;
}
