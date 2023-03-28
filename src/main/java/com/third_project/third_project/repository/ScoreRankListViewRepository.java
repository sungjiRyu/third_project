package com.third_project.third_project.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.third_project.third_project.entity.ScoreRankListView;

public interface ScoreRankListViewRepository extends JpaRepository<ScoreRankListView,Long> {
  @Query(value = "select is_mi_seq, et_name, is_time, PERCENT_RANK() OVER (ORDER BY is_time asc) from score_list_view order by et_name", nativeQuery = true)
  public List<ScoreRankListView> findByIsMiSeqAndEtName (String etName,Long isMiSeq);
  List<ScoreRankListView> findByIsMiSeq(Long isMiSeq);
}
