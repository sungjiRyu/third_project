package com.third_project.third_project.game.controller;

import com.third_project.third_project.game.service.StampService;
import com.third_project.third_project.game.vo.StampInfoResponseVO;
import com.third_project.third_project.game.vo.StampResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "스텀프 정보", description = "스텀프 CRUD")
@RestController
@RequestMapping("/api/game/stamp")
@RequiredArgsConstructor
public class StampAPIController {
    private final StampService sService;

    @Operation(summary = "스텀프 정보 조회", description = "회원 번호를 통해 스텀스 정보 조회")
    @GetMapping("/{seq}")
    public ResponseEntity<StampInfoResponseVO> getStampInfo(@Parameter(description = "회원번호",example = "1") @PathVariable Long seq){
        StampInfoResponseVO response = sService.getStampInfo(seq);
        return new ResponseEntity<>(response, response.getCode());
    }

    @Operation(summary = "스텀프 사용 기능", description = "회원 번호를 통해 스텀프 사용 기능")
    @PatchMapping("/use/{seq}")
    public ResponseEntity<StampResponseVO> patchStampUse(@Parameter(description = "회원번호",example = "1") @PathVariable Long seq){
        StampResponseVO response = sService.patchStampUse(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
}
