package com.third_project.third_project.repository;

import com.third_project.third_project.entity.SumScoreNameViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SumScoreNameViewRepo extends JpaRepository<SumScoreNameViewEntity, Long> {
    List<SumScoreNameViewEntity> findByMiSeq(Long miSeq);
}
