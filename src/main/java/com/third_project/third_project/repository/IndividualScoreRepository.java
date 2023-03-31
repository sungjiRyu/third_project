package com.third_project.third_project.repository;



import com.third_project.third_project.Detail.vo.IsSumScoreVO;
import com.third_project.third_project.entity.IndividualScoreEntity;
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
  @Query(value = "select * from individual_score where is_et_seq = :isEtSeq", nativeQuery = true) 
  public IndividualScoreEntity findByIsEtSeq (@Param("isEtSeq") Long isEtSeq);

  public List<GetExRecodVO> findByIsMiSeq(Long miSeq);

  @Query("SELECT isEtSeq AS isEtSeq, SEC_TO_TIME(SUM(TIME_TO_SEC(isTime))) AS isTime FROM IndividualScoreEntity WHERE isMiSeq = :miSeq AND isEtSeq IN (5, 6, 7, 8, 9, 10, 11, 12) AND isRegDt BETWEEN :startDate AND :endDate GROUP BY isEtSeq")
List<GetExerciseTimeVO> getExerciseTimeByPeriod(@Param("miSeq") Long miSeq, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
// @Query("SELECT new com.third_project.third_project.entity.IndividualScoreEntity(null, null, isEtSeq, null, SEC_TO_TIME(SUM(TIME_TO_SEC(isTime))), null, null, null, null) FROM IndividualScoreEntity WHERE isMiSeq = :miSeq AND isEtSeq IN (5, 6, 7, 8, 9, 10, 11, 12) AND isRegDt BETWEEN :startDate AND :endDate GROUP BY isEtSeq")
// List<IndividualScoreEntity> getExerciseTimeByPeriod(@Param("miSeq") Long miSeq, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

  @Query(value = "select b.mi_nickname, c.et_name, sum(a.is_time) as total from individual_score a join member_info b on a.is_mi_seq = b.mi_seq join ex_type c "
  +"on a.is_et_seq = c.et_seq where week(is_reg_dt) = week(now()) and mi_seq = :seq and et_name = :name", nativeQuery = true)
  List<IsSumScoreVO> findSumScoreName(@Param("seq")Long seq, @Param("name")String name);

  @Query(value = "select b.mi_nickname, date(a.is_reg_dt), sum(a.is_time) as total from individual_score a join member_info b on a.is_mi_seq = b.mi_seq join ex_type c "
  +"on a.is_et_seq = c.et_seq where mi_seq = :seq and date(is_reg_dt) = :date", nativeQuery = true)
  List<IsSumScoreVO> findSumScoreDate(@Param("seq")Long seq, @Param("date")LocalDate date);
}
