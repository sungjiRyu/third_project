package com.third_project.third_project.Admin.service;

import java.lang.reflect.Member;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.third_project.third_project.Admin.entity.AdminRankView;
import com.third_project.third_project.Admin.repository.AdminRankViewRepository;
import com.third_project.third_project.Admin.vo.AdminGameScoreListVO;
import com.third_project.third_project.entity.GameScoreEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.game.vo.BasicResponseVO;
import com.third_project.third_project.game.vo.ScorePercentResponseVO;
import com.third_project.third_project.game.vo.WeeklyRankingVO;
import com.third_project.third_project.repository.GameScoreRepository;
import com.third_project.third_project.repository.MemberInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminGameScoreService {
  private final AdminRankViewRepository adminRankViewRepository;
  private final GameScoreRepository gsRepo;
  private final MemberInfoRepository miRepo;
  
  public AdminGameScoreListVO getAdminGameScoreList(String keyword, Pageable pageable){
    Page<AdminRankView> page = adminRankViewRepository.findByEtNameContains(keyword, pageable);
    AdminGameScoreListVO response = AdminGameScoreListVO.builder()
    .list(page.getContent())
    .total(page.getTotalElements())
    .totalPage(page.getTotalPages())
    .currentPage(page.getNumber())
    .build();
    return response;
  }

  public Map<String,Object> selectPercentInfo(Long et_seq){
    Map<String,Object> map = new LinkedHashMap<String,Object>();
    Optional<AdminRankView> entityOpt = adminRankViewRepository.findById(et_seq);
    if(entityOpt.isEmpty()){
      map.put("status", false);
    }
    else{
      map.put("status", true);
      map.put("no",entityOpt.get().getGsSeq());
    }
    return map;
  }
  
  
    }
  


