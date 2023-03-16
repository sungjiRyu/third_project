package com.third_project.third_project.game.service;

import com.third_project.third_project.entity.*;
import com.third_project.third_project.game.vo.RankListResponseVO;
import com.third_project.third_project.game.vo.ScorePercentResponseVO;
import com.third_project.third_project.game.vo.ScoreResponseVO;
import com.third_project.third_project.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;



import java.time.LocalDate;
import java.util.List;



@Service
@RequiredArgsConstructor
public class ScoreService {
    private final GameScoreRepository gsRepo;
    private final MemberInfoRepository miRepo;
    private final MemberScoreViewRepository msRepo;
    private final MemberRankingViewRepository mrRepo;
    private final TripleRankingViewRepository tRepo;

    public ScorePercentResponseVO getMemberPercent(LocalDate day, Long seq){
        MemberInfoEntity member = miRepo.findByMiSeq(seq);
        GameScoreEntity entity = gsRepo.findByMember(member);
        List<GameScoreEntity> list = gsRepo.findByGsRegDt(day);

        if(entity == null) {
            ScorePercentResponseVO response = ScorePercentResponseVO.builder()
                    .status(false)
                    .message("조회된 회원 정보가 없습니다")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return response;
        }
        Integer people = 0;
        for(int i=0; i<list.size(); i++){
            people += 1;
        }


        ScorePercentResponseVO response = ScorePercentResponseVO.builder()
                .status(true)
                .message("조회된 회원 성적의 상위 퍼센트 조회!!")
                .code(HttpStatus.OK)
                .build();
        return response;
    }

    public ScoreResponseVO getMemberScore(Long seq) { //회원 게임점수 조회
        MemberInfoEntity member = miRepo.findByMiSeq(seq); //입력받은 번호에 해당하는 회원 조회
        GameScoreEntity mentity = gsRepo.findByMember(member);
        MemberRankingView mrentity = mrRepo.findByMiSeq(seq);

        ScoreResponseVO response = new ScoreResponseVO();

        if(mentity == null) {
            response = ScoreResponseVO.builder()
                    .status(false)
                    .message("조회된 회원 정보가 없습니다")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();

            return response;
        }

        else if(mrentity == null) {
            response = ScoreResponseVO.builder()
                    .status(false)
                    .message("저번주의 기록이 없습니다")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();

            return response;
        }

        response = ScoreResponseVO.builder()
                    .status(true)
                    .message("지난주의 성적입니다")
                    .code(HttpStatus.OK)
                    .score(mrentity.getGsTime())
                    .name(mrentity.getMiNickName())
                    .rank(mrentity.getRank())
                    .url(mrentity.getMingUrl())
                    .ban(mrentity.getMiClass())
                    .build();

        return response;
    }

    public List<RankListResponseVO> getTotalScore(Long seq) { // 전체 1~3등 순위 조회
//        TripleRankingView tentity = tRepo.findByEtSeq(seq);
        List<RankListResponseVO> list = new ArrayList<RankListResponseVO>();

        for(TripleRankingView data : tRepo.findByEtSeq(seq)) {
            RankListResponseVO vo = new RankListResponseVO(data);
            list.add(vo);
        }
        return list;
    }
}

