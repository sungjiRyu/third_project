package com.third_project.third_project.Detail.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import com.third_project.third_project.Detail.vo.IndividualScoreInsertVO;
import com.third_project.third_project.Detail.vo.IndividualScoreResponseVO;
import com.third_project.third_project.Detail.vo.updateIndividualScoreInsertVO;
import com.third_project.third_project.entity.ExTypeEntity;
import com.third_project.third_project.entity.IndividualScoreEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.repository.ExTypeRepository;
import com.third_project.third_project.repository.IndividualScoreRepository;
import com.third_project.third_project.repository.MemberInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IndividualScoreService {
  private final IndividualScoreRepository isRepo;
  private final MemberInfoRepository miRepo;
  private final ExTypeRepository etRepo;

  
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
      IndividualScoreEntity entity = IndividualScoreEntity.builder()
      .member(miRepo.findById(data.getIsMiSeq()).get())
      .exType(etRepo.findById(data.getIsEtSeq()).get())
      .isRegDt(data.getIsRegDt())
      .isTime(data.getIsTime())
      .build();
      isRepo.save(entity);
      
    }
    return IndividualScoreResponseVO.builder().code(HttpStatus.OK).message("저장되었습니다.").status(true).build();
  }

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
    return IndividualScoreResponseVO.builder().code(HttpStatus.OK).message("수정되었습니다.").status(true).build();
  }
}
