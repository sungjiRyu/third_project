package com.third_project.third_project.main.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.third_project.third_project.entity.IndividualScoreEntity;
import com.third_project.third_project.main.service.ExcriseService;
import com.third_project.third_project.main.vo.response.GetExRecodVO;
import com.third_project.third_project.main.vo.response.GetExTimePeriodVO;
import com.third_project.third_project.main.vo.response.GetExVO;
import com.third_project.third_project.main.vo.response.GetPersonalExListVO;
import com.third_project.third_project.main.vo.response.PostExRecord;
import com.third_project.third_project.main.vo.response.ResponseMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "개인 측정용 운동 API" , description ="개인측정용 운동목록조회/저장/개인기록조회")
@RestController
@RequestMapping("api/exercise")
public class ExcriseAPIController {
    @Autowired ExcriseService exercise;

    @Operation(summary = "개인 측정용 운동 리스트 조회", description = "개인 측정용 운동 목록을 조회합니다.")
    @GetMapping("")
    public ResponseEntity<List<GetPersonalExListVO>> getExList(){
        List<GetPersonalExListVO> response = exercise.getExList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "개인 측정용 운동 기록 저장", description = "time -> 00:00:10 형식으로 데이터를 넣어주세요.")
    @PostMapping("")
    public ResponseEntity<ResponseMessage> postExRecord(@RequestBody PostExRecord data){
        ResponseMessage response = exercise.postExRecord(data);
        return new ResponseEntity<>(response, (HttpStatus)response.getCode());
    }
    
    @Operation(summary = "개인 측정용 운동 기록 조회", description = "5(걷기), 6(사이클링), 7(요가), 8(댄스) ,9(코어트레이닝), 10(필라테스), 11(수영), 12(하이킹)")
    @GetMapping("/personal/{miSeq}")
    public ResponseEntity<List<Object[]>> getExRecord(
        @Parameter(description = "현재 로그인한 회원seq", example = "1") @PathVariable Long miSeq,
        @Parameter(description = "시작일" , example = "2023-03-21" ) @RequestParam LocalDate startDate,
        @Parameter(description = "종료일" , example = "2023-03-22") @RequestParam LocalDate endDate
        ){
        List<Object[]> response = exercise.getExRecod(miSeq, startDate, endDate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}