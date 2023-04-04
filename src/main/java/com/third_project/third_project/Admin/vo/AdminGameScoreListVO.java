package com.third_project.third_project.Admin.vo;

import java.util.List;

import com.third_project.third_project.Admin.entity.AdminRankView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminGameScoreListVO {
  private List<AdminRankView> list;
  private Long total;
  private Integer totalPage;
  private Integer currentPage;
}
