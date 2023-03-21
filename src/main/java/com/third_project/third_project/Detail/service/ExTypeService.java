package com.third_project.third_project.Detail.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.third_project.third_project.Detail.vo.ExTypeResponseVO;
import com.third_project.third_project.Detail.vo.ExtypeInsertVO;
import com.third_project.third_project.entity.ExTypeEntity;
import com.third_project.third_project.repository.ExImgRepository;
import com.third_project.third_project.repository.ExLevelRepository;
import com.third_project.third_project.repository.ExTypeRepository;
import com.third_project.third_project.repository.GenInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExTypeService {
  private final ExTypeRepository etRepo;
  private final GenInfoRepository genRepo;
  private final ExLevelRepository elRepo;
  private final ExImgRepository exiRepo;

    public ExTypeResponseVO addExType(ExtypeInsertVO data){
      if(data == null){
        ExTypeResponseVO response = ExTypeResponseVO.builder()
        .code(HttpStatus.BAD_REQUEST)
        .status(false)
        .message("운동 정보 등록에 실패했습니다..")
        .build();
        return response;
      }
      ExTypeEntity entity = ExTypeEntity.builder()
      .etName(data.getEtName())
      .etDetail(data.getEtDetail())
      .etEsSeq(data.getEtEsSeq())
      .etTimeType(data.getEtTimeType())
      .gen(genRepo.findByGiSeq(data.getGen()))
      .level(elRepo.findByLevelSeq(data.getLevel()))
      .img(exiRepo.findByEimgSeq(data.getEimg()))
      .build();
      etRepo.save(entity);
      System.out.println(entity);
      ExTypeResponseVO response = ExTypeResponseVO.builder()
      .code(HttpStatus.OK)
      .status(true)
      .message("운동 정보가 등록되었습니다.")
      .build();
      return response;
    }
    public ExTypeResponseVO deldeteExTypeInfo(Long no){
      etRepo.deleteById(no);
      ExTypeResponseVO response = ExTypeResponseVO.builder()
      .code(HttpStatus.OK)
      .status(true)
      .message("삭제되었습니다.")
      .build();
      return response;
    }

  }

