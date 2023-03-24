package com.third_project.third_project.repository;


import com.third_project.third_project.entity.ExTypeEntity;
import com.third_project.third_project.entity.IndividualScoreEntity;
import com.third_project.third_project.main.vo.response.GetExRecodVO;
import com.third_project.third_project.main.vo.response.GetExTimePeriodVO;

import jakarta.persistence.ConstructorResult;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IndividualScoreRepository extends JpaRepository<IndividualScoreEntity, Long> {
  public IndividualScoreEntity findByIsSeq (Long isSeq);
  @Query(value = "select * from individual_score where is_et_seq = :isEtSeq", nativeQuery = true) 
  public IndividualScoreEntity findByIsEtSeq (@Param("isEtSeq") Long isEtSeq);

  public List<GetExRecodVO> findByIsMiSeq(Long miSeq);

  @Query("SELECT isEtSeq AS isEtSeq, SEC_TO_TIME(SUM(TIME_TO_SEC(isTime))) AS isTime FROM IndividualScoreEntity WHERE isMiSeq = :miSeq AND isEtSeq IN (5, 6, 7, 8, 9, 10, 11, 12) AND isRegDt BETWEEN :startDate AND :endDate GROUP BY isEtSeq")
 
List<Object[]> getExerciseTimeByPeriod(@Param("miSeq") Long miSeq, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
