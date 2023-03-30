package com.third_project.third_project.game.controller;
import com.third_project.third_project.game.service.ScoreService;
import com.third_project.third_project.game.service.StampService;
import com.third_project.third_project.game.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

import java.util.List;

@Tag(name = "게임성적 관리", description = "게임성적 CRUD")
@RestController
@RequestMapping("api/game/score")
@RequiredArgsConstructor
public class ScoreAPIController {

    private final ScoreService scoreService;

    @Operation(summary = "지난주 회원 게임성적 조희", description = "seq 통해 해당 회원의 지난 주 게임성적을 조회합니다")
    @GetMapping("/{seq}")
    public ResponseEntity<ScoreResponseVO> getUserInfo(
            @Parameter(description = "회원번호", example = "1") @PathVariable Long seq){
        return new ResponseEntity<>(scoreService.getMemberScore(seq), HttpStatus.OK);
    }
    @Operation(summary = "지난주 전체 게임성적 조희", description = "운동 seq 통해 지난 주 해당 운동의 게임성적을 1~3위까지 조회합니다")
    @GetMapping("/total/{seq}")
    public ResponseEntity<RankResponseVO> getTotalScore(
            @Parameter(description = "운동 종류 번호", example = "1") @PathVariable Long seq){
        return new ResponseEntity<>(scoreService.getTotalScore(seq), HttpStatus.OK);
    }
    @Operation(summary = "게임 기록 입력", description = "게임 기록 정보를 추가합니다. score는 \"score\": \"00:00:10\" 형식으로 입력합니다")
    @PutMapping("/insert/score")
    public ResponseEntity<GameResponseVO> insertGameScore(@RequestBody GameScoreInsertVO data) {
        return new ResponseEntity<>(scoreService.insertGameRecord(data), HttpStatus.OK);
    }

    @Operation(summary = "회원 게임성적 상위 % 조회", description = "운동 번호와 회원 번호를 통해 저번 주 게임 성적 상위 % 조회 기능")
    @GetMapping("/percent/{seq}/{miSeq}")
    public ResponseEntity<ScorePercentResponseVO> getMemberPercent(@Parameter(description = "운동 번호", example = "1") @PathVariable Long seq,
                                                                   @Parameter(description = "회원 번호", example = "1") @PathVariable Long miSeq){
        ScorePercentResponseVO response = scoreService.getMemberPercent(seq, miSeq);
        return new ResponseEntity<>(response, response.getCode());
    }

    @Operation(summary = "게임 인증영상 등록", description = "회원번호와 회원의 게임 인증영상을 등록합니다 filename과 uri은 입력 안해도 됩니다")
    @PutMapping(value="/insert/video", consumes= MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameResponseVO> insertGameVideo(
            @Parameter(description = "formdata로 영상 데이터를 입력합니다")
            @ModelAttribute VideoResponseVO data
            ) {
        return new ResponseEntity<>(scoreService.insertGameVideo(data), HttpStatus.OK);
    }

//    @GetMapping("/available/{seq}")
//    public ResponseEntity<BasicResponseVO> setAvailableStamp(@PathVariable Long seq){
//        BasicResponseVO response = scoreService.setAvailableStamp(seq);
//        return new ResponseEntity<>(response, response.getCode());
//    }
    @Operation(summary = "전체 게임 성적 상위 % 입력", description = "운동 번호를 통해 저번 주 게임 성적 상위 % 테이블에 저장 기능")
    @PatchMapping("/addPercent/{seq}")
    public ResponseEntity<BasicResponseVO> setAvailableStamp(@Parameter(description = "운동번호", example = "2") @PathVariable Long seq){
        BasicResponseVO response = scoreService.setPercent(seq);
        return new ResponseEntity<>(response, response.getCode());
    }

}
