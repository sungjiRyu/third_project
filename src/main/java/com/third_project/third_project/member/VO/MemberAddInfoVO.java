package com.third_project.third_project.member.VO;

import com.third_project.third_project.entity.MemberImgEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MemberAddInfoVO {
//            private String Nickname;
            private Integer Tall;
            private Integer Weight;
            private String ClassNum;
            private Long giSeq;
            private Long esSeq;
            private MemberImgEntity ming;
}
