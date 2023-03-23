package com.third_project.third_project.game.controller;

import com.third_project.third_project.game.service.StampService;
import com.third_project.third_project.game.vo.BasicResponseVO;
import com.third_project.third_project.game.vo.GoodsResponseVO;
import com.third_project.third_project.game.vo.StampInfoResponseVO;
import com.third_project.third_project.game.vo.StampResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "스탬프 정보", description = "스탬프 CRUD")
@RestController
@RequestMapping("/api/game/stamp")
@RequiredArgsConstructor
public class StampAPIController {
    private final StampService sService;

    @Operation(summary = "스탬프 정보 조회", description = "회원 번호를 통해 스탬스 정보 조회")
    @GetMapping("/{seq}")
    public ResponseEntity<StampInfoResponseVO> getStampInfo(@Parameter(description = "회원번호",example = "1") @PathVariable Long seq){
        StampInfoResponseVO response = sService.getStampInfo(seq);
        return new ResponseEntity<>(response, response.getCode());
    }

    @Operation(summary = "스탬프 사용 기능", description = "회원 번호를 통해 스탬프 사용 기능")
    @PatchMapping("/use/{seq}")
    public ResponseEntity<StampResponseVO> patchStampUse(@Parameter(description = "회원번호",example = "1") @PathVariable Long seq){
        StampResponseVO response = sService.patchStampUse(seq);
        return new ResponseEntity<>(response, response.getCode());
    }

    @Operation(summary = "회원의 보유 상품 정보 조회", description = "회원 번호를 통해 보유한 상품 정보 조회")
    @GetMapping("/goods/{seq}")
    public ResponseEntity<GoodsResponseVO> getMemberGoods(@Parameter(description = "회원번호", example = "1") @PathVariable Long seq){
        GoodsResponseVO response = sService.getMemberGoods(seq);
        return new ResponseEntity<>(response, response.getCode());
    }

    @Operation(summary = "상위 %를 조회해 스탬프 사용 기회 추가 기능", description = "운동 번호를 통해 스탬프 기회 추가")
    @PutMapping("/addStamp/{seq}")
    public ResponseEntity<BasicResponseVO> putStampAvailable(@Parameter(description = "운동번호", example = "1") @PathVariable Long seq){
        BasicResponseVO response = sService.putStampAvailable(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
}
