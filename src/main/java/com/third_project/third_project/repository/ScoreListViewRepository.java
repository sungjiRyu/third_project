package com.third_project.third_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.third_project.third_project.entity.ScoreListView;

public interface ScoreListViewRepository extends JpaRepository<ScoreListView,Long> {
  
  List<ScoreListView> findByIsMiSeq(Long isMiSeq);
  List<ScoreListView> findByEtName(String etName);
}