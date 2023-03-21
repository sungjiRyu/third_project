package com.third_project.third_project.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.third_project.third_project.entity.ExTypeEntity;
import com.third_project.third_project.entity.ExVideoEntity;
import com.third_project.third_project.entity.IndividualScoreEntity;
import com.third_project.third_project.main.vo.ErrorResponse;
import com.third_project.third_project.main.vo.exceptionHendler.ExceptionHendler;
import com.third_project.third_project.main.vo.response.GetExRecodVO;
import com.third_project.third_project.main.vo.response.GetExVO;
import com.third_project.third_project.main.vo.response.PostExRecord;
import com.third_project.third_project.main.vo.response.ResponseMessage;
import com.third_project.third_project.repository.ExTypeRepository;
import com.third_project.third_project.repository.ExVideoRepository;
import com.third_project.third_project.repository.GameNoticeRepository;
import com.third_project.third_project.repository.IndividualScoreRepository;

@Service
public class ExcriseService {
    @Autowired GameNoticeRepository gameNoticeRepo;
    @Autowired ExVideoRepository exVideoRepo;
    @Autowired ExTypeRepository exTypeRepo;
    @Autowired IndividualScoreRepository individualScoreRepo;

    // 개인측정용 운동 리스트로 출력
    public List<GetExVO> getExList(){
        List<GetExVO> data = exTypeRepo.findByEtEsSeq(Long.valueOf(4));
        return data;
    }

    // 개인측정기록 저장
    public ResponseMessage postExRecord(PostExRecord data){
        ResponseMessage response = null;
        IndividualScoreEntity individualRecord = null;
        ExTypeEntity exType = exTypeRepo.findByEtSeq(data.getEtSeq());

        if (data.getMiSeq() == null) 
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("로그인을 해주세요")));
        else if (data.getEtSeq() == null) 
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("운동 종목을 입력하세요")));
        else if (exType.getEtEsSeq() != 4)
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("잘못된 운동종목입니다")));
        else if (exType.getEtEsSeq() == null)
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("존재하지않는 운동종목입니다")));
        else if (data.getTime() == null) 
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("측정한 시간을 입력하세요")));
        else{
            individualRecord = IndividualScoreEntity.builder()
            .isMiSeq(data.getMiSeq())
            .isEtSeq(data.getEtSeq())
            .isTime(data.getTime())
            .build();
            individualScoreRepo.save(individualRecord);
            response = new ResponseMessage(true, HttpStatus.OK, "등록되었습니다.");
        }
        return response;
    }

    // 개인 기록 조회하기
    // 회원 시퀀스로 검색해서 개인 운동기록 조회 (리스트에 담아서 가져오기)
    public List<GetExRecodVO> getExRecod(Long miSeq){
        List<GetExRecodVO> exRecod = individualScoreRepo.findByIsMiSeq(miSeq);

        return exRecod;
    }
}
