package com.third_project.third_project.game.controller;

import com.third_project.third_project.game.service.ScoreService;
import com.third_project.third_project.game.vo.BasicResponseVO;
import com.third_project.third_project.game.vo.ScorePercentResponseVO;
import com.third_project.third_project.game.vo.ScoreResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@Tag(name = "게임성적 조회", description = "성적 CRUD")
@RestController
@RequestMapping("api/game/score")
@RequiredArgsConstructor
public class ScoreAPIController {

    private final ScoreService scoreService;

    @Operation(summary = "회원 게임성적 조희", description = "seq 통해 해당 회원의 게임성적을 조회합니다")
    @GetMapping("/{seq}")
    public ResponseEntity<ScoreResponseVO> getUserInfo(
            @PathVariable Long seq){
        return new ResponseEntity<>(scoreService.getMemberScore(seq), HttpStatus.OK);
    }

    @Operation(summary = "회원 게임성적 상위 % 조회", description = "운동 번호와 회원 번호를 통해 저번 주 게임 성적 상위 % 조회 기능")
    @GetMapping("/percent/{seq}/{miSeq}")
    public ResponseEntity<ScorePercentResponseVO> getMemberPercent(@Parameter(description = "운동 번호", example = "1") @PathVariable Long seq,
                                                                   @Parameter(description = "회원 번호", example = "1") @PathVariable Long miSeq){
        ScorePercentResponseVO response = scoreService.getMemberPercent(seq, miSeq);
        return new ResponseEntity<>(response, response.getCode());
    }

    @GetMapping("/available/{seq}")
    public ResponseEntity<BasicResponseVO> setAvailableStamp(@PathVariable Long seq){
        BasicResponseVO response = scoreService.setAvailableStamp(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
}
