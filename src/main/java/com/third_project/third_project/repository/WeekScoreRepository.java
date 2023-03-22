package com.third_project.third_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.third_project.third_project.entity.WeekScore;

public interface WeekScoreRepository extends JpaRepository<WeekScore,Long> {
  List<WeekScore> findByIsMiSeq(Long isMiSeq);
  List<WeekScore> findByIsWeek(Long isWeek);
}
