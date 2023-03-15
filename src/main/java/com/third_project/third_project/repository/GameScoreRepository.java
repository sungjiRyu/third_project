package com.third_project.third_project.repository;

import com.third_project.third_project.entity.GameScoreEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
=======
import java.time.LocalDate;
>>>>>>> hia7
import java.time.LocalTime;
import java.util.List;

public interface GameScoreRepository extends JpaRepository<GameScoreEntity, Long> {
    GameScoreEntity findByMember(MemberInfoEntity member);
<<<<<<< HEAD
    GameScoreEntity findByGsTime(LocalTime gsTime);
    List<GameScoreEntity> findAllByOrderByGsTimeAsc();
=======
    List<GameScoreEntity> findByGsTimeOrderByAsc(LocalTime gsTime);
    List<GameScoreEntity> findByGsTimeOrderByDesc(LocalTime gsTime);
    List<GameScoreEntity> findByGsRegDt(LocalDate gsRegDt);
>>>>>>> hia7
}
