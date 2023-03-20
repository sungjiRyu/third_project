package com.third_project.third_project.game.controller;

import com.third_project.third_project.game.service.ScoreService;
import com.third_project.third_project.game.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "게임 기록 입력", description = "게임 기록 정보를 추가합니다.") //시간 값 json 에러남 확인필요 
    @PutMapping("/insert")
    public ResponseEntity<GameResponseVO> insertGameScore(@RequestBody GameScoreInsertVO data) {
        return new ResponseEntity<>(scoreService.insertGameRecord(data), HttpStatus.OK);
    }
}
