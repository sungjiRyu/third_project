package com.third_project.third_project.game.service;

import com.third_project.third_project.entity.*;
import com.third_project.third_project.game.exception.ErrorResponse;
import com.third_project.third_project.game.exception.GameScoreException;
import com.third_project.third_project.game.vo.*;
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
    private final ExTypeRepository eRepo;

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
                    .nickname(mrentity.getMiNickName())
                    .rank(mrentity.getRank())
                    .url(mrentity.getMingUrl())
                    .ban(mrentity.getMiClass())
                    .build();

        return response;
    }

    public RankResponseVO getTotalScore(Long seq) { // 전체 1~3등 순위 조회
        List<TripleRankingView> tlist = tRepo.findByEtSeq(seq);
        List<RankListResponseVO> entity = new ArrayList<>();

        for(int i=0; i< tlist.size(); i++) {
            RankListResponseVO vo = RankListResponseVO.builder()
                    .ban(tlist.get(i).getMiClass())
                    .nickname(tlist.get(i).getMiNickName())
                    .rank(tlist.get(i).getRank())
                    .score(tlist.get(i).getGsTime())
                    .url(tlist.get(i).getMingUrl())
                    .build();
            entity.add(vo);
        }


        if(tlist.isEmpty()) { // list의 값이 비었을때 예외처리
            RankResponseVO response = RankResponseVO.builder()
                    .status(false)
                    .message("해당 운동은 저번주에 진행되지 않았습니다")
                    .code(HttpStatus.BAD_REQUEST).build();
            return response;
        }
        RankResponseVO response = RankResponseVO.builder()
                .status(true)
                .message("지난주 상위 3위까지의 기록입니다")
                .code(HttpStatus.OK)
                .list(entity)
                .build();
        return response;
    }

    public GameResponseVO insertGameRecord(GameScoreInsertVO data) {
        GameResponseVO response = new GameResponseVO();
        MemberInfoEntity entity = miRepo.findByMiSeq(data.getMiSeq());
        ExTypeEntity exentity = eRepo.findByEtSeq(data.getEtSeq());
        GameScoreEntity score = GameScoreEntity.builder()
                .member(entity)
                .exType(exentity)
                .gsTime(data.getScore())
                .build();

        response = GameResponseVO.builder()
                .status(true)
                .message("기록 등록이 완료되었습니다")
                .code(HttpStatus.OK)
                .build();

        gsRepo.save(score);
        return response;
    }
}

