package com.third_project.third_project.repository;


import com.third_project.third_project.entity.ExTypeEntity;
import com.third_project.third_project.entity.IndividualScoreEntity;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IndividualScoreRepository extends JpaRepository<IndividualScoreEntity, Long> {
  public IndividualScoreEntity findByIsSeq (Long isSeq);
  IndividualScoreEntity findByExType(ExTypeEntity exType);

}