package com.third_project.third_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.third_project.third_project.entity.WeeklyScoreView;

public interface WeeklyScoreViewRepository extends JpaRepository<WeeklyScoreView,Long> {
  List<WeeklyScoreView> findByIsMiSeq(Long isMiSeq);
  List<WeeklyScoreView> findByIsMiSeqAndIsWeek(Long isMiSeq,Integer isWeek);
}
