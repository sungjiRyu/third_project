package com.third_project.third_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.third_project.third_project.entity.ExStatusEntity;

public interface ExStatusRepository extends JpaRepository<ExStatusEntity,Long> {
  public ExStatusEntity findByEsSeq(Long seq);
}
