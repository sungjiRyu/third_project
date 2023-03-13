package com.third_project.third_project.repository;

import com.third_project.third_project.entity.GameNoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameNoticeRepository extends JpaRepository<GameNoticeEntity, Long> {
}
