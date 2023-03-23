package com.third_project.third_project.Detail.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;



import org.springframework.http.HttpStatus;

import com.third_project.third_project.Detail.vo.ExTypeResponseVO;
import com.third_project.third_project.Detail.vo.IndividualScoreInsertVO;
import com.third_project.third_project.Detail.vo.IndividualScoreResponseVO;
import com.third_project.third_project.Detail.vo.ScoreListViewResponseVO;
import com.third_project.third_project.Detail.vo.WeeklyScoreViewVO;
import com.third_project.third_project.Detail.vo.updateIndividualScoreInsertVO;
import com.third_project.third_project.entity.IndividualScoreEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.entity.ScoreListView;
import com.third_project.third_project.entity.WeekScore;
import com.third_project.third_project.entity.WeeklyScoreView;
import com.third_project.third_project.repository.ExTypeRepository;
import com.third_project.third_project.repository.IndividualScoreRepository;
import com.third_project.third_project.repository.MemberInfoRepository;
import com.third_project.third_project.repository.ScoreListViewRepository;
import com.third_project.third_project.repository.WeekScoreRepository;
import com.third_project.third_project.repository.WeeklyScoreViewRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IndividualScoreService {
  private final IndividualScoreRepository isRepo;
  private final MemberInfoRepository miRepo;
  private final ExTypeRepository etRepo;
  private final ScoreListViewRepository slRepo;
  private final WeeklyScoreViewRepository wsRepo;
  

  //개인 기록 추가
  public IndividualScoreResponseVO addIndividualScore(IndividualScoreInsertVO data){
    if(data == null){
      IndividualScoreResponseVO response = IndividualScoreResponseVO.builder()
      .code(HttpStatus.BAD_REQUEST)
      .message("내용을 입력해주세요")
      .status(false)
      .build();
      return response;
    }
    else{
      if (miRepo.findById(data.getIsMiSeq()).isEmpty()) return IndividualScoreResponseVO.builder().code(HttpStatus.BAD_REQUEST).message("miRepo error").status(false).build();
      else if (etRepo.findById(data.getIsEtSeq()).isEmpty()) return IndividualScoreResponseVO.builder().code(HttpStatus.BAD_REQUEST).message("etRepo error").status(false).build();
      else {
        IndividualScoreEntity entity = IndividualScoreEntity.builder()
        // .member(miRepo.findById(data.getIsMiSeq()).get())
        // .exType(etRepo.findById(data.getIsEtSeq()).get())
        .isMiSeq(miRepo.findById(data.getIsMiSeq()).get().getMiSeq())
        .isEtSeq(etRepo.findById(data.getIsEtSeq()).get().getEtSeq())
        .isRegDt(data.getIsRegDt())
        .isTime(data.getIsTime())
        .isWeed(data.getIsWeek())
        .build();
        isRepo.save(entity);
      }
    }
    return IndividualScoreResponseVO.builder().code(HttpStatus.OK).message("개인 기록이 추가되었습니다..").status(true).build();
  }
// 개인 기록 수정
  public IndividualScoreResponseVO updateIndividualScore(updateIndividualScoreInsertVO data,Long siSeq){
    
    IndividualScoreEntity entity = isRepo.findById(siSeq).get();
    if(data.getUpateIsEtSeq() == null){
      data.setUpateIsEtSeq(entity.getExType().getEtSeq());
    }
    if(data.getUpateIsTime() == null){
      data.setUpateIsTime(entity.getIsTime());
    }
    entity.setExType(etRepo.findById(data.getUpateIsEtSeq()).get());
    entity.setIsTime(data.getUpateIsTime());
    isRepo.save(entity);
    return IndividualScoreResponseVO.builder().code(HttpStatus.OK).message("개인 기록이 수정되었습니다..").status(true).build();
  }

  //개인기록 삭제
  @Transactional
  public IndividualScoreResponseVO deleteIndividualScore(Long siSeq){
    isRepo.deleteById(siSeq);
    return IndividualScoreResponseVO.builder().code(HttpStatus.OK).message("개인 기록이 삭제되었습니다..").status(true).build();
  }

 //개인 기록 조회
public ScoreListViewResponseVO getListScore(Long memberNo){
List<ScoreListView> member = slRepo.findByIsMiSeq(memberNo);
  // ScoreListView member = slRepo.findByIsMiSeq(memberNo);
  if(member.isEmpty()){
    ScoreListViewResponseVO response = ScoreListViewResponseVO.builder()
    .code(HttpStatus.NOT_FOUND)
    .message(memberNo+"학생은 등록되지 않은 회원입니다.")
    .status(true)
    .build();
    return response;
}
ScoreListViewResponseVO response = ScoreListViewResponseVO.builder()
  .list(member)
  .code(HttpStatus.OK)
  .message(memberNo+"학생의 기록이 조회되었습니다.")
  .status(true)
  .build();
  return response;
}
//이번주 기록 조회
public WeeklyScoreViewVO getWeeklyScore(Long memberNo, Integer week){
  List<WeeklyScoreView> member = wsRepo.findByIsMiSeq(memberNo);
  if(member.isEmpty()){
    WeeklyScoreViewVO response = WeeklyScoreViewVO.builder()
    .message(memberNo+"학생은 등록되지 않은 회원입니다.")
    .code(HttpStatus.NOT_FOUND)
    .status(false)
    .build();
    return response;
  }
  List<WeeklyScoreView> weekly = wsRepo.findByIsMiSeqAndIsWeek(memberNo, week);
  WeeklyScoreViewVO response = WeeklyScoreViewVO.builder()
  .list(weekly)
  .message(memberNo+"학생의 주간 기록이 조회되었습니다..")
  .code(HttpStatus.OK)
  .status(true)
  .build();
  return response;
}
  }


