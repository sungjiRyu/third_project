package com.third_project.third_project.Admin.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.third_project.third_project.Admin.service.NoticeAdminService;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class main {
  private final NoticeAdminService noticeAdminService;
  @GetMapping("/")
  public String getMain(Model model, @RequestParam@Nullable String keyword,
  @PageableDefault(size = 10, sort="gnSeq",direction = Sort.Direction.DESC)Pageable pageable
  ,HttpSession session){
    if(keyword == null) keyword = "";
    model.addAttribute("result", noticeAdminService.getNoticeList(keyword, pageable));
    model.addAttribute("keyword", keyword);
    return "/main";
  }
  @GetMapping("/add")
  public String getNoticeAdd(){
    return "/add";
  }
}
