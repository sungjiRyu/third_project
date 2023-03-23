package com.third_project.third_project.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.third_project.third_project.main.service.ExcriseService;
import com.third_project.third_project.main.vo.response.GetExRecodVO;
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

    @Operation(summary = "개인 측정용 운동 기록 저장", description = "측정한 기록을 저장합니다.")
    @PostMapping("")
    public ResponseEntity<ResponseMessage> postExRecord(@RequestBody PostExRecord data){
        ResponseMessage response = exercise.postExRecord(data);
        return new ResponseEntity<>(response, (HttpStatus)response.getCode());
    }
    
    @Operation(summary = "개인 측정용 운동 기록 조회", description = "작업중.")
    @GetMapping("{miSeq}")
    public ResponseEntity<List<GetExRecodVO>> getExRecord(@Parameter(description = "현재 로그인한 회원seq", example = "1") @PathVariable Long miSeq){
        List<GetExRecodVO> response = exercise.getExRecod(miSeq);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
