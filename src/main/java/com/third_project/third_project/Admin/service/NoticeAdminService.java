package com.third_project.third_project.Admin.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.third_project.third_project.entity.GameNoticeEntity;
import com.third_project.third_project.repository.GameNoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeAdminService {
  private final GameNoticeRepository gameNoticeRepository;
  public Map<String,Object> getNoticeList(){
    Map<String,Object> map = new LinkedHashMap<String,Object>();
    // Page<GameNoticeEntity> entity = gameNoticeRepository.
  }
}
