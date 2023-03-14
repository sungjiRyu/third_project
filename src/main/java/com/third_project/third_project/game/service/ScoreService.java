package com.third_project.third_project.game.service;

import com.third_project.third_project.entity.GameScoreEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.game.vo.ScoreResponseVO;
import com.third_project.third_project.repository.GameScoreRepository;
import com.third_project.third_project.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScoreService {
    private final GameScoreRepository gsRepo;
    private final MemberInfoRepository miRepo;

    public ScoreResponseVO getMemberScore(Long seq) { //회원 게임점수 조회
        MemberInfoEntity entity = miRepo.findByMiSeq(seq); //입력받은 번호에 해당하는 회원 조회
        

        return null;
    }
}
