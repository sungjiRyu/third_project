package com.third_project.third_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.third_project.third_project.entity.IndividualScoreRankView;

public interface IndividualScoreRankViewRepository extends JpaRepository<IndividualScoreRankView,Long> {
  List<IndividualScoreRankView> findByIsMiSeq(Long isMiSeq);
}
