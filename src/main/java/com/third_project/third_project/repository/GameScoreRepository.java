package com.third_project.third_project.repository;

import com.third_project.third_project.entity.GameScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameScoreRepository extends JpaRepository<GameScoreEntity, Long> {
}