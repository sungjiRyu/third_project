package com.third_project.third_project.member.VO;

import com.third_project.third_project.entity.ExStatusEntity;
import com.third_project.third_project.entity.GenInfoEntity;
import com.third_project.third_project.entity.MemberImgEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSearchVO {
    private String id;
    private Integer tall;
    private Integer weight;
    private String nickname;
    private String classnum;
    private String gen;
    private String type;
    private String mimg;


}
