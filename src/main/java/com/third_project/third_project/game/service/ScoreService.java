package com.third_project.third_project.game.service;

import com.third_project.third_project.entity.ExTypeEntity;
import com.third_project.third_project.entity.GameScoreEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.game.vo.BasicResponseVO;
import com.third_project.third_project.game.vo.ScorePercentResponseVO;
import com.third_project.third_project.entity.MemberScoreView;
import com.third_project.third_project.game.vo.ScoreResponseVO;
import com.third_project.third_project.game.vo.WeeklyRankingVO;
import com.third_project.third_project.repository.ExTypeRepository;
import com.third_project.third_project.repository.GameScoreRepository;
import com.third_project.third_project.repository.MemberInfoRepository;
import com.third_project.third_project.repository.MemberScoreViewRepository;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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


    // 게임 성적 상위 % 조회 후 스템프 사용 횟수 부여 기능
//    public BasicResponseVO setAvailableStamp(Long seq){
//        List<WeeklyRankingVO> ranking = gsRepo.findRanking(seq);
//        List<WeeklyRankingVO> list = new ArrayList<>();
//        if(ranking.isEmpty()){
//            BasicResponseVO response = BasicResponseVO.builder()
//                    .status(false)
//                    .message("해당 운동 정보는 저번 회차 게임 운동 종목과 일치하지 않습니다.")
//                    .code(HttpStatus.BAD_REQUEST)
//                    .build();
//        }
//
//
//        for(int i=0; i<ranking.size(); i++){
//            ranking.get(i).getRanking();
//        System.out.println(ranking.get(i).getRanking());
//        }
//
//
//        BasicResponseVO response = BasicResponseVO.builder()
//                .status(true)
//                .message("횟수 부여 완료")
//                .code(HttpStatus.OK)
//                .build();
//        return response;
//    }


    // 게임 성적 상위 몇 퍼센트 인지 조회 기능
    // ranking 부분 interface return 형식으로 수정 필요해 보임
    public ScorePercentResponseVO getMemberPercent(Long seq, Long miSeq){
        List<GameScoreEntity> list = gsRepo.findWeeklyRanking(seq);
        List<WeeklyRankingVO> ranking = gsRepo.findRanking(seq);
        MemberInfoEntity member = miRepo.findByMiSeq(miSeq);
        if(list.isEmpty()) {
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

        Integer rank = 0;
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getMember().getMiSeq() == member.getMiSeq()){
                rank = ranking.get(i).getRanking();
            }
        }
        Double percent = (double)rank / (double) people * 100.0;
        DecimalFormat df = new DecimalFormat("#.##");
        ScorePercentResponseVO response = ScorePercentResponseVO.builder()
                .status(true)
                .message("조회된 회원 성적의 상위 퍼센트 조회!!")
                .code(HttpStatus.OK)
                .percent(df.format(percent))
                .build();
        return response;
    }



    public ScoreResponseVO getMemberScore(Long seq) { //회원 게임점수 조회
        MemberInfoEntity member = miRepo.findByMiSeq(seq); //입력받은 번호에 해당하는 회원 조회
        GameScoreEntity mentity = gsRepo.findByMember(member);
        MemberScoreView msentity = msRepo.findByMiSeq(seq);


        if(mentity == null) {
            ScoreResponseVO response = ScoreResponseVO.builder()
                    .status(false)
                    .message("조회된 회원 정보가 없습니다")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();

            return response;
        }

        if(msentity.getEtTimeType()==1){
            List<MemberScoreView> times = msRepo.findAllByOrderByGsTimeAsc();
            int rank = (int) times.stream()
                    .filter(time -> time.getGsTime() == msentity.getGsTime())
                    .count();

            ScoreResponseVO response = ScoreResponseVO.builder()
                    .status(true)
                    .message("지난주의 성적입니다")
                    .code(HttpStatus.OK)
                    .score(msentity.getGsTime())
                    .name(msentity.getMiNickName())
                    .grade(rank+1)
                    .build();

            return response;

        }
        if(msentity.getEtTimeType()==2){
            List<MemberScoreView> times = msRepo.findAllByOrderByGsTimeDesc();
            int rank = (int) times.stream()
                    .filter(time -> time.getGsTime() == msentity.getGsTime())
                    .count();

            ScoreResponseVO response = ScoreResponseVO.builder()
                    .status(true)
                    .message("지난주의 성적입니다")
                    .code(HttpStatus.OK)
                    .score(msentity.getGsTime())
                    .name(msentity.getMiNickName())
                    .grade(rank+1)
                    .build();

            return response;
        }

        return null;
    }

}
