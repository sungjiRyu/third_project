package com.third_project.third_project.member;

import com.third_project.third_project.member.VO.MemberAddVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원관련", description = "회원 CRUD")
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService mService;

    // Create : 멤버 추가
   // @Operation(summary = "멤버 추가")
   // @PutMapping("")
   // public MemberAddVO memberAdd(MemberAddVO data) {
   //     return mService.memberAdd(data);
   // }
    // Read
    // Update
    // Delete


}
