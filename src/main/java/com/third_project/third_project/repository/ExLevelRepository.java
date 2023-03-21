package com.third_project.third_project.repository;

import com.third_project.third_project.entity.ExLevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExLevelRepository extends JpaRepository<ExLevelEntity, Long> {
  ExLevelEntity findByLevelSeq (Long levelSeq);
}
