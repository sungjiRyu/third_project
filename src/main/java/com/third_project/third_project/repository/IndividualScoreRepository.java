package com.third_project.third_project.repository;



import com.third_project.third_project.entity.IndividualScoreEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.main.vo.response.GetExRecodVO;
import com.third_project.third_project.main.vo.response.GetExTimePeriodVO;
import com.third_project.third_project.main.vo.response.GetExerciseTimeVO;

import jakarta.persistence.ConstructorResult;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IndividualScoreRepository extends JpaRepository<IndividualScoreEntity, Long> {
  public IndividualScoreEntity findByIsSeq (Long isSeq);


  public List<GetExRecodVO> findByIsMiSeq(Long miSeq);
  public List<IndividualScoreEntity> findByMember(MemberInfoEntity member);


  @Query("SELECT isEtSeq AS isEtSeq, SEC_TO_TIME(SUM(TIME_TO_SEC(isTime))) AS isTime FROM IndividualScoreEntity WHERE isMiSeq = :miSeq AND isEtSeq IN (5, 6, 7, 8, 9, 10, 11, 12) AND isRegDt BETWEEN :startDate AND :endDate GROUP BY isEtSeq")
List<GetExerciseTimeVO> getExerciseTimeByPeriod(@Param("miSeq") Long miSeq, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
// @Query("SELECT new com.third_project.third_project.entity.IndividualScoreEntity(null, null, isEtSeq, null, SEC_TO_TIME(SUM(TIME_TO_SEC(isTime))), null, null, null, null) FROM IndividualScoreEntity WHERE isMiSeq = :miSeq AND isEtSeq IN (5, 6, 7, 8, 9, 10, 11, 12) AND isRegDt BETWEEN :startDate AND :endDate GROUP BY isEtSeq")
// List<IndividualScoreEntity> getExerciseTimeByPeriod(@Param("miSeq") Long miSeq, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
