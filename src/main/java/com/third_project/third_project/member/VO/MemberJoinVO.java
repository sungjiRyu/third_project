package com.third_project.third_project.member.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberJoinVO {
    private String id;
    private String pwd;
    private String confirmpwd;
    private String nickname;
}
