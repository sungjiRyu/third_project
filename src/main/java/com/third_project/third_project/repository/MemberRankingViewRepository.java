package com.third_project.third_project.repository;

import com.third_project.third_project.entity.MemberRankingView;
import com.third_project.third_project.entity.MemberScoreView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRankingViewRepository extends JpaRepository<MemberRankingView, Long> {
    MemberRankingView findByMiSeq(Long miSeq);
}
