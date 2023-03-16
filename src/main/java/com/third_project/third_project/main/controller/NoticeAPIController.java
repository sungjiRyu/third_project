package com.third_project.third_project.main.controller;

import com.third_project.third_project.entity.GameNoticeEntity;
import com.third_project.third_project.main.service.NoticeService;
import com.third_project.third_project.main.vo.request.POSTGameNoticeVO;
import com.third_project.third_project.main.vo.response.GetDetailNoticeVO;
import com.third_project.third_project.main.vo.response.GetNoticeVO;
import com.third_project.third_project.main.vo.response.POSTNoticeVO;
import com.third_project.third_project.main.vo.response.ResponseMessage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "게임 공지사항 API" , description ="게임 공지사항 등록/조회 API.")
@RestController
@RequestMapping("/notice")
public class NoticeAPIController {
    @Autowired NoticeService noticeService;

    @Operation(summary = "게임 공지사항 등록", description = "게임 공지사항을 등록합니다.")
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> writeArticle(@ModelAttribute POSTGameNoticeVO data){
        ResponseMessage response = noticeService.postNotice(data);
        return new ResponseEntity<>(response, (HttpStatusCode)response.getCode());
    }

    @Operation(summary = "게임 공지사항 조회(제목과 등록일, 운동종목이 출력)", description = "게임 공지사항 제목이 리스트로 출력됩니다.")
    @GetMapping("")
    public ResponseEntity<List<GetNoticeVO>> getArticle(
        @Parameter(description = "page default=0", example = "0") @RequestParam @Nullable Integer page,
        @Parameter(description = "size defult=10", example = "10") @RequestParam @Nullable Integer size
    ){
        List<GetNoticeVO> response = noticeService.GetNotice(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(summary =  "게임 공지사항 상세조회", description = "공지사항 내용 상세조회.")
    @GetMapping("/detail")
    public ResponseEntity<GetDetailNoticeVO> GetNoticeDetail(
        @Parameter(description = "공지사항 번호", example = "1") @RequestParam Long gnSeq
    ){
        GetDetailNoticeVO response = noticeService.GetDetailNotice(gnSeq);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
    
