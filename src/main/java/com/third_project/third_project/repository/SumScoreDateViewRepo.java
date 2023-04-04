package com.third_project.third_project.repository;

import com.third_project.third_project.entity.SumScoreDateViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SumScoreDateViewRepo extends JpaRepository<SumScoreDateViewEntity, Long> {
    List<SumScoreDateViewEntity> findByMiSeq(Long miSeq);
}
