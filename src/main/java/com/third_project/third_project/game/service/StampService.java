package com.third_project.third_project.game.service;

import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.entity.StampInfoEntity;
import com.third_project.third_project.game.vo.StampInfoResponseVO;
import com.third_project.third_project.game.vo.StampResponseVO;
import com.third_project.third_project.repository.MemberInfoRepository;
import com.third_project.third_project.repository.StampInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StampService {
    private final StampInfoRepository sRepo;
    private final MemberInfoRepository mRepo;

    public StampInfoResponseVO getStampInfo(Long seq){
        MemberInfoEntity member = mRepo.findByMiSeq(seq);
        StampInfoEntity entity = sRepo.findByMember(member);
        if(entity == null){
            StampInfoResponseVO response = StampInfoResponseVO.builder()
                    .status(false)
                    .message("조회된 회원 정보가 없습니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return response;
        }
        StampInfoResponseVO response = StampInfoResponseVO.builder()
                .status(true)
                .message("회원의 스텀프 정보 조회!!")
                .code(HttpStatus.OK)
                .total(entity.getStampTotal())
                .ava(entity.getStampAva())
                .use(entity.getUse())
                .build();
        return response;
    }
}
