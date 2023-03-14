package com.third_project.third_project.member;

import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.member.VO.MemberAddVO;
import com.third_project.third_project.repository.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MemberService {
    //private final MemberInfoEntity miEntity;


    private final MemberInfoRepository miRepo;
    private final GenInfoRepository giRepo;
    private final ClassInfoRepository ciRepo;
    private final ExStatusRepository esRepo;
    private final MemberImgRepository mimgRepo;


    // Create
    public MemberAddVO addMember(MemberAddVO data) {
        MemberInfoEntity miEntity = MemberInfoEntity.builder()
                .miId(data.getId())
                .miPwd(data.getPwd())
                .miTall(data.getTall())
                .miWeight(data.getWeight())
                .miNickname(data.getNickname())
                .miRole(data.getRole())
                .gen(giRepo.findByGiSeq(data.getGiSeq()))
                .classNum(ciRepo.findByCiSeq(data.getCiSeq()))
                .exStatus(esRepo.findByEsSeq(data.getEsSeq()))
                .mimg(mimgRepo.findByMimgSeq(data.getMimgSeq()))
                .build();
        miRepo.save(miEntity);

        MemberAddVO addMemberVO = MemberAddVO.builder()
                .status(true)
                .message("추가하였습니다")
                .code(HttpStatus.ACCEPTED)
                .build();
        return addMemberVO;

    }
    // Read
    // Update
    // Delete
}
