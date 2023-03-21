package com.third_project.third_project.Detail.api;

import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.third_project.third_project.Detail.service.ExTypeService;
import com.third_project.third_project.Detail.vo.ExTypeResponseVO;
import com.third_project.third_project.Detail.vo.ExtypeInsertVO;
import com.third_project.third_project.entity.ExTypeEntity;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
@Tag(name = "운동 타입", description = "운동타입 CRUD" )
@RestController
@RequestMapping("/api/extype")
@RequiredArgsConstructor
public class ExTypeController {
  private final ExTypeService etService;


  
  @PutMapping("")
  public ResponseEntity<ExTypeResponseVO> putExTypeInfo(@RequestBody ExtypeInsertVO data){
    return new ResponseEntity<ExTypeResponseVO>(etService.addExType(data),HttpStatus.OK);
  }

  @DeleteMapping("{no}")
  public ResponseEntity<ExTypeResponseVO> deleteExType(@PathVariable Long no){
    return new ResponseEntity<ExTypeResponseVO>(etService.deldeteExTypeInfo(no), HttpStatus.OK);
  }
  }

