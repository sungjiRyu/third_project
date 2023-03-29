package com.third_project.third_project.hiaTest;

import com.third_project.third_project.entity.GameScoreEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.game.vo.BasicResponseVO;
import com.third_project.third_project.game.vo.ScorePercentResponseVO;
import com.third_project.third_project.game.vo.WeeklyRankingVO;
import com.third_project.third_project.repository.GameScoreRepository;
import com.third_project.third_project.repository.MemberInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

@SpringBootTest
public class HiaGameTest {
@Autowired
    GameScoreRepository gsRepo;
@Autowired
    MemberInfoRepository miRepo;

    @Test
    public void setPercent(){
        List<GameScoreEntity> ranking = gsRepo.findWeeklyRanking(1L);
        if(ranking.isEmpty()){
            BasicResponseVO response = BasicResponseVO.builder()
                    .status(false)
                    .message("해당 운동 정보는 저번 회차 게임 운동 종목과 일치하지 않습니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            System.out.println(response.getStatus());
            System.out.println(response.getMessage());
            System.out.println(response.getCode());
        }
        for(int i=0; i<ranking.size(); i++){
            GameScoreEntity entity = gsRepo.findByMember(ranking.get(i).getMember());
            entity.setMember(ranking.get(i).getMember());
            entity.setExType(ranking.get(i).getExType());
            entity.setGsTime(ranking.get(i).getGsTime());
            gsRepo.save(entity);
        }
        BasicResponseVO response = BasicResponseVO.builder()
                .status(true)
                .message("퍼센트 정보 입력 완료")
                .code(HttpStatus.OK)
                .build();
        System.out.println(response.getStatus());
        System.out.println(response.getMessage());
        System.out.println(response.getCode());
    }

    @Test
    public void getMemberPercent(){
        List<GameScoreEntity> list = gsRepo.findWeeklyRanking(1L);
        List<WeeklyRankingVO> ranking = gsRepo.findRanking(1L);
        MemberInfoEntity member = miRepo.findByMiSeq(1L);
        if(list.isEmpty()){
            ScorePercentResponseVO response = ScorePercentResponseVO.builder()
                    .status(false)
                    .message("조회된 운동 정보가 없습니다")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            System.out.println(response.getStatus());
            System.out.println(response.getMessage());
            System.out.println(response.getCode());
        }
        if(member == null){
            ScorePercentResponseVO response = ScorePercentResponseVO.builder()
                    .status(false)
                    .message("조회된 회원 정보가 없습니다")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            System.out.println(response.getStatus());
            System.out.println(response.getMessage());
            System.out.println(response.getCode());
        }
        Integer people = 0;
        for(int i=0; i<list.size(); i++){
            people += 1;
        }
        System.out.println(people);
        Integer rank = 0;
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getMember().getMiSeq() == member.getMiSeq()){
                rank = ranking.get(i).getRanking();
            }
        }
        Double percent = (double)rank / (double) people * 100.0;
        ScorePercentResponseVO response = ScorePercentResponseVO.builder()
                .status(true)
                .message("조회된 회원 성적의 상위 퍼센트 조회!!")
                .code(HttpStatus.OK)
                .percent(percent)
                .build();
        System.out.println(response.getStatus());
        System.out.println(response.getMessage());
        System.out.println(response.getCode());
    }
}
