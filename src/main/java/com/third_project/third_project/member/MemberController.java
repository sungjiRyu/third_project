package com.third_project.third_project.member;

import com.third_project.third_project.member.VO.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "회원관련", description = "회원 CRUD")
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    //@Value("${file.image.exercise.member}") String local_img_path;
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

    @Operation(summary = "회원 정보 조회")
    @GetMapping("/{seq}")
    public ResponseEntity<MemberSearchResponseVO> searchMember(@PathVariable Long seq) {
        return  new ResponseEntity<MemberSearchResponseVO>(mService.searchMember(seq), HttpStatus.OK);
    }

    // http://localhost:8888/api/member/img/{seq}
    @Operation(summary = "멤버 이미지 업로드")
    @Transactional
    @PutMapping("/img/{seq}")
    public ResponseEntity<MemberImgResponseVO> MemberImgAdd(@PathVariable Long seq, MultipartFile file) {
        return new ResponseEntity<MemberImgResponseVO>(mService.addMemberImg(seq, file), HttpStatus.OK);
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseVO> login (@RequestBody MemberLoginVO loginVO) throws Exception{
        return new ResponseEntity<MemberLoginResponseVO>(mService.login(loginVO), HttpStatus.OK);
    }

    @Operation(summary = "로그아웃")
    @GetMapping("/logout")
    public ResponseEntity<MemberLogoutResponseVO> logout () {
        return new ResponseEntity<MemberLogoutResponseVO>(mService.logout(), HttpStatus.OK);
    }
}
