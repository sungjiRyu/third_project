package com.third_project.third_project.Admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class main {
  
  @GetMapping("/")
  public String getMain(){
    return "/main";
  }
  @GetMapping("/add")
  public String getNoticeAdd(){
    return "/add";
  }
}
