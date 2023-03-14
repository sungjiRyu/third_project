package com.third_project.third_project.repository;

import com.third_project.third_project.entity.GameScoreEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface GameScoreRepository extends JpaRepository<GameScoreEntity, Long> {
    GameScoreEntity findByMember(MemberInfoEntity member);
    GameScoreEntity findByGsTime(LocalTime gsTime);
    List<GameScoreEntity> findAllByOrderByGsTimeAsc();
}
