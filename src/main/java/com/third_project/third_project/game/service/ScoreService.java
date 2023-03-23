package com.third_project.third_project.game.service;

import com.third_project.third_project.entity.*;
import com.third_project.third_project.game.vo.*;
import com.third_project.third_project.entity.ExTypeEntity;
import com.third_project.third_project.entity.GameScoreEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.repository.*;
import com.third_project.third_project.repository.ExTypeRepository;
import com.third_project.third_project.repository.GameScoreRepository;
import com.third_project.third_project.repository.MemberInfoRepository;
import com.third_project.third_project.repository.MemberScoreViewRepository;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final CertificationVideoReposritory cvRepo;

    @Value("${file.video.exercise.game}") String game_video_path;



    // 스템프 사용 횟수 부여를 위한 전체 게임 성적 상위 % 입력 기능
    public BasicResponseVO setPercent(Long seq){
        List<GameScoreEntity> ranking = gsRepo.findWeeklyRanking(seq);
        if(ranking.isEmpty()){
            BasicResponseVO response = BasicResponseVO.builder()
                    .status(false)
                    .message("해당 운동 정보는 저번 회차 게임 운동 종목과 일치하지 않습니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return response;
        }

        for(int i=0; i<ranking.size(); i++){
        GameScoreEntity entity = gsRepo.findByMember(ranking.get(i).getMember());
                entity.setGsPercent(getMemberPercent(seq, ranking.get(i).getMember().getMiSeq()).getPercent());
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
        return response;
    }


    // 게임 성적 상위 몇 퍼센트 인지 조회 기능
    public ScorePercentResponseVO getMemberPercent(Long seq, Long miSeq){
        List<GameScoreEntity> list = gsRepo.findWeeklyRanking(seq);
        List<WeeklyRankingVO> ranking = gsRepo.findRanking(seq);
        MemberInfoEntity member = miRepo.findByMiSeq(miSeq);
        if(list.isEmpty()) {
            ScorePercentResponseVO response = ScorePercentResponseVO.builder()
                    .status(false)
                    .message("조회된 운동 정보가 없습니다")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return response;
        }
        if(member == null){
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
        System.out.println(people);
        Integer rank = 0;
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getMember().getMiSeq() == member.getMiSeq()){
                rank = ranking.get(i).getRanking();
            }
        }
        System.out.println(rank);
        Double percent = (double)rank / (double) people * 100.0;
//        DecimalFormat df = new DecimalFormat("#.##");
        ScorePercentResponseVO response = ScorePercentResponseVO.builder()
                .status(true)
                .message("조회된 회원 성적의 상위 퍼센트 조회!!")
                .code(HttpStatus.OK)
                .percent(percent)
                .build();
        return response;
    }

    public ScoreResponseVO getMemberScore(Long seq) { //회원 게임점수 조회
//        MemberInfoEntity member = miRepo.findByMiSeq(seq); //입력받은 번호에 해당하는 회원 조회
        MemberRankingView mrentity = mrRepo.findByMiSeq(seq);
//        GameScoreEntity mentity = gsRepo.findByMemberRankingView(mrentity);

        ScoreResponseVO response = new ScoreResponseVO();

//        if(mrentity == null) {
//            response = ScoreResponseVO.builder()
//                    .status(false)
//                    .message("조회된 회원 정보가 없습니다")
//                    .code(HttpStatus.BAD_REQUEST)
//                    .build();
//
//            return response;
//        }

        if(mrentity == null) {
            response = ScoreResponseVO.builder()
                    .status(false)
                    .message("저번주의 기록이 없습니다")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();

            return response;
        }

        else if(mrentity.getMingUrl() == null) {
            response = ScoreResponseVO.builder()
                    .status(true)
                    .message("지난주의 성적입니다")
                    .code(HttpStatus.OK)
                    .score(mrentity.getGsTime())
                    .nickname(mrentity.getMiNickName())
                    .rank(mrentity.getRank())
                    .url(null)
                    .ban(mrentity.getMiClassNum())
                    .build();
        }
        else {
            response = ScoreResponseVO.builder()
                    .status(true)
                    .message("지난주의 성적입니다")
                    .code(HttpStatus.OK)
                    .score(mrentity.getGsTime())
                    .nickname(mrentity.getMiNickName())
                    .rank(mrentity.getRank())
                    .url(mrentity.getMingUrl())
                    .ban(mrentity.getMiClassNum())
                    .build();
        }

        return response;
    }

    public RankResponseVO getTotalScore(Long seq) { // 전체 1~3등 순위 조회
        List<TripleRankingView> tlist = tRepo.findByEtSeq(seq);
        List<RankListResponseVO> entity = new ArrayList<>();

        for(int i=0; i< tlist.size(); i++) {
            RankListResponseVO vo = RankListResponseVO.builder()
                    .ban(tlist.get(i).getMiClassNum())
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

    public GameResponseVO insertGameRecord(GameScoreInsertVO data) { //회원 게임 기록 등록
        GameResponseVO response = new GameResponseVO();
        MemberInfoEntity entity = miRepo.findByMiSeq(data.getMiSeq());
        ExTypeEntity exentity = eRepo.findByEtSeq(data.getEtSeq());
        
        if(entity==null) {
            response = GameResponseVO.builder()
                    .status(false)
                    .message("해당 회원은 존재하지 않습니다")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return response;
        }

        else if(exentity==null) {
            response = GameResponseVO.builder()
                    .status(false)
                    .message("해당 운동은 존재하지 않습니다")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return response;
        }
        
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

    public GameResponseVO insertGameVideo(VideoResponseVO data) { // 회원 인증영상 등록
        GameResponseVO response = new GameResponseVO();
        MemberInfoEntity entity = miRepo.findByMiSeq(data.getMiSeq());

        if(entity==null) {
            response = GameResponseVO.builder()
                    .status(false)
                    .message("해당 회원은 존재하지 않습니다")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return response;
        }

        String originalFileName = data.getVideo().getOriginalFilename();
        String[] split = originalFileName.split("\\.");
        String ext = split[split.length -1];
        String filename = "";
        for(int i=0; i<split.length-1; i++) {
            filename += split[i];
        }
        String newUri = "game_"+entity.getMiSeq()+LocalDateTime.now().getNano();
        String saveFilename = "game_" + entity.getMiSeq()+"_"+LocalDateTime.now().getNano() + "." +ext;

        Path forderLocation = Paths.get(game_video_path);
        Path targetFile = forderLocation.resolve(saveFilename);

        try {
            Files.copy(data.getVideo().getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception e) {
            response = GameResponseVO.builder()
                    .status(false)
                    .message("파일전송에 실패했습니다")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        CertificationVideoEntity vdoEntity = CertificationVideoEntity.builder()
                .member(entity)
                .cvUrl(newUri)
                .cvName(saveFilename)
                .build();

        cvRepo.save(vdoEntity);
        response = GameResponseVO.builder()
                .status(true)
                .message("영상 등록이 완료되었습니다")
                .code(HttpStatus.OK)
                .build();

        return response;
    }
}

