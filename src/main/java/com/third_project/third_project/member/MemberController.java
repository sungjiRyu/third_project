package com.third_project.third_project.member;

import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.member.VO.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원관련", description = "회원 CRUD")
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService mService;


    //http://localhost:8888/api/member/join
    @Operation(summary = " 가입 (필수 정보)")
    @PutMapping("/join")
    public ResponseEntity<MemberJoinResponseVO> joinMember(@RequestBody MemberJoinVO data) {
        return new ResponseEntity<>(mService.joinMember(data), HttpStatus.OK);
    }

    //http://localhost:8888/api/member/addinfo/{seq}
    @Operation(summary = " 추가정보 입력")
    @PatchMapping("/addinfo/{seq}")
    public ResponseEntity<MemberAddInfoResponseVO> addInfo(@PathVariable Long seq, @RequestBody MemberAddInfoVO data) {
        return new ResponseEntity<>(mService.addInfo(seq, data), HttpStatus.OK);
    }


    // http://localhost:8888/api/member/{seq}
    @Operation(summary = "멤버 정보 수정")
    @PatchMapping("/{seq}")
    public ResponseEntity<MemberUpdateResponseVO> updateMember(@PathVariable Long seq, @RequestBody MemberUpdateVO data) {
        return new ResponseEntity<>(mService.updateMember(seq, data), HttpStatus.OK);
    }

    // http://localhost:8888/api/member/{seq}
    @Operation(summary = "멤버 탈퇴")
    @Transactional
    @DeleteMapping("/{seq}")
    public ResponseEntity<MemberDeleteResponseVO> deleteMember(@PathVariable Long seq, @RequestBody MemberDeleteVO data) {
        return new ResponseEntity<MemberDeleteResponseVO>(mService.deleteMember(seq, data), HttpStatus.OK);
    }
}
