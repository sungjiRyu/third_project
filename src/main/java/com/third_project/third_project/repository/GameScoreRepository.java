package com.third_project.third_project.repository;

import com.third_project.third_project.entity.ExTypeEntity;
import com.third_project.third_project.entity.GameScoreEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;




import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface GameScoreRepository extends JpaRepository<GameScoreEntity, Long> {
    GameScoreEntity findByMember(MemberInfoEntity member);

    GameScoreEntity findByExType (ExTypeEntity exType);
    GameScoreEntity findByGsTime(LocalTime gsTime);
    List<GameScoreEntity> findAllByOrderByGsTimeAsc();

//    List<GameScoreEntity> findByGsTimeOrderByAsc(LocalTime gsTime);
//    List<GameScoreEntity> findByGsTimeOrderByDesc(LocalTime gsTime);
    List<GameScoreEntity> findByGsRegDt(LocalDate gsRegDt);


}
