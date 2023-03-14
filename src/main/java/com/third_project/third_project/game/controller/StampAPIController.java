package com.third_project.third_project.game.controller;

import com.third_project.third_project.game.service.StampService;
import com.third_project.third_project.game.vo.StampInfoResponseVO;
import com.third_project.third_project.game.vo.StampResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game/stamp")
@RequiredArgsConstructor
public class StampAPIController {
    private final StampService sService;

    @Operation(summary = "스텀프 정보 조회", description = "회원 번호를 통해 스텀스 정보 조회")
    @GetMapping("/{seq}")
    public ResponseEntity<StampInfoResponseVO> getStampInfo(@PathVariable Long seq){
        StampInfoResponseVO response = sService.getStampInfo(seq);
        return new ResponseEntity<>(response, response.getCode());
    }

}
