package com.third_project.third_project.repository;

import com.third_project.third_project.entity.GameScoreEntity;
import com.third_project.third_project.entity.MemberScoreView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberScoreViewRepository extends JpaRepository<MemberScoreView, Long> {
    MemberScoreView findByEtTimeType(Integer etTimeType);
    MemberScoreView findByMiSeq(Long miSeq);
//    List<MemberScoreView> findByMemberOrderByDfcDate(MemberInfoEntity var1);
    List<MemberScoreView> findAllByOrderByGsTimeAsc();
    List<MemberScoreView> findAllByOrderByGsTimeDesc();
}
