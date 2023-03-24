package com.third_project.third_project.Detail.api;


import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.third_project.third_project.Detail.service.IndividualScoreService;
import com.third_project.third_project.Detail.vo.IndividualScoreInsertVO;
import com.third_project.third_project.Detail.vo.IndividualScoreResponseVO;
import com.third_project.third_project.Detail.vo.ScoreListViewResponseVO;
import com.third_project.third_project.Detail.vo.WeeklyScoreViewVO;
import com.third_project.third_project.Detail.vo.updateIndividualScoreInsertVO;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "개인 기록 관리", description = "개인 기록 CRUD API")
@RestController
@RequestMapping("api/individualscore")
@RequiredArgsConstructor
public class IndividualScoreController {
  private final IndividualScoreService isService;
  @Operation(summary = "개인 기록 추가", description = "isTime은 \"isTime\": \"00:00:10\" 형식으로 입력합니다")
  @PutMapping("")
  public ResponseEntity<IndividualScoreResponseVO> addIndividualScore(
    @Parameter(description = "RequestBody로 데이터를 입력합니다.(isMiSeq:회원 번호,isEtSeq: 운동 종류 번호, isRegDt:기록 작성일, isTime: 기록") @RequestBody IndividualScoreInsertVO data){
  return new ResponseEntity<IndividualScoreResponseVO>(isService.addIndividualScore(data),HttpStatus.OK);
  }
  @Operation(summary = "개인 기록 수정", description = "개인 기록이 수정되었습니다.")
  @PatchMapping("")
  public ResponseEntity<IndividualScoreResponseVO> updateIndividualScore(
    @Parameter(description = "RequestBody로 데이터를 입력합니다.(upateIsEtSeq: 변경할 운동 종류 번호, upateIsTime: 변경할 기록")@RequestBody updateIndividualScoreInsertVO data,
    @Parameter(description = "수정할 글 번호")@RequestParam Long isSeq  
  ){
  return new ResponseEntity<IndividualScoreResponseVO>(isService.updateIndividualScore(data, isSeq),HttpStatus.OK);
    }
  @Operation(summary = "개인 기록 삭제", description = "개인 기록이 삭제되었습니다.")
  @DeleteMapping("{isSeq}")
  public ResponseEntity<IndividualScoreResponseVO> deleteIndividualScore(@PathVariable Long siSeq){
    return new ResponseEntity<IndividualScoreResponseVO>(isService.deleteIndividualScore(siSeq),HttpStatus.OK);
  }
  @Operation(summary = "개인 기록 조회", description="개인 기록이 조회되었습니다.")
  @GetMapping("/list")
  public ResponseEntity<ScoreListViewResponseVO> getListScore(
    @Parameter(description = "조회할 회원 번호")@RequestParam Long memberNo){
  return new ResponseEntity<ScoreListViewResponseVO>(isService.getListScore(memberNo),HttpStatus.OK);
}
@Operation(summary = "이번주 기록 조회")
@GetMapping("/list/week")
public ResponseEntity<WeeklyScoreViewVO> getWeekScore(
  @Parameter(description = "회원 번호")@RequestParam Long memberNo,
  @Parameter(description = "주(週)")@RequestParam Integer week){
  return new ResponseEntity<WeeklyScoreViewVO>(isService.getWeeklyScore(memberNo, week),HttpStatus.OK);
}
@Operation(summary = "종목별 시간 변화량")
@GetMapping("/list/change")
public ResponseEntity<ScoreListViewResponseVO> getChangeList(
  @Parameter(description = "회원번호")@RequestParam Long memberNo,
  @Parameter(description = "종목 명")@RequestParam String type){
  return new ResponseEntity<ScoreListViewResponseVO>(isService.getLevelVariance(memberNo, type),HttpStatus.OK);
}
}




