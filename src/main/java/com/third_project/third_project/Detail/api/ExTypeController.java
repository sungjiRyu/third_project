package com.third_project.third_project.Detail.api;

import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.third_project.third_project.Detail.service.ExTypeService;
import com.third_project.third_project.Detail.vo.ExTypeResponseVO;
import com.third_project.third_project.Detail.vo.ExtypeInsertVO;



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
//  @GetMapping("/{type}/{filename}")
//  public ResponseEntity<Resource> getImageFile(
//          @PathVariable String type, @PathVariable String filename
//  ) throws Exception {
//      return exiService.getImageFile(type, filename);
//  }
  @DeleteMapping("{no}")
  public ResponseEntity<ExTypeResponseVO> deleteExType(@PathVariable Long no){
    return new ResponseEntity<ExTypeResponseVO>(etService.deldeteExTypeInfo(no), HttpStatus.OK);
  }
  }

