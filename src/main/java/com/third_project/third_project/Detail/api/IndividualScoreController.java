package com.third_project.third_project.Detail.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.third_project.third_project.Detail.service.IndividualScoreService;
import com.third_project.third_project.Detail.vo.IndividualScoreInsertVO;
import com.third_project.third_project.Detail.vo.IndividualScoreResponseVO;
import com.third_project.third_project.Detail.vo.updateIndividualScoreInsertVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/individualscore")
@RequiredArgsConstructor
public class IndividualScoreController {
private final IndividualScoreService isService;
@PutMapping("")
public ResponseEntity<IndividualScoreResponseVO> addIndividualScore(@RequestBody IndividualScoreInsertVO data){
  return new ResponseEntity<IndividualScoreResponseVO>(isService.addIndividualScore(data),HttpStatus.OK);
}
  @PatchMapping("")
    public ResponseEntity<IndividualScoreResponseVO> updateIndividualScore(@RequestBody updateIndividualScoreInsertVO data, @RequestParam Long isSeq  
){
  return new ResponseEntity<IndividualScoreResponseVO>(isService.updateIndividualScore(data, isSeq),HttpStatus.OK);
    }
  
  
}

