package com.third_project.third_project.member.VO;

import com.third_project.third_project.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberAddResponseVO {
    private String id;
    private String pwd;
    private Integer tall;
    private Integer weight;
    private String nickname;
    private String role;
    private String classnum;

    private Long giSeq;
    private Long esSeq;
    private Long mimgSeq;

    private Boolean status;
    private String message;
    private HttpStatus code;

    public MemberAddResponseVO(MemberInfoEntity entity) {
        this.id       = entity.getMiId();
        this.pwd      = entity.getMiPwd();
        this.tall     = entity.getMiTall();
        this.weight   = entity.getMiWeight();
        this.nickname = entity.getMiNickname();
        this.role     = entity.getMiRole();
        this.giSeq    = entity.getGen().getGiSeq();
        this.classnum = entity.getMiClassNum();
        this.esSeq    = entity.getExStatus().getEsSeq();
        this.mimgSeq  = entity.getMimg().getMimgSeq();

    }

}
