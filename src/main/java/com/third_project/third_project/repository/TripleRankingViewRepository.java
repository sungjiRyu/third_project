package com.third_project.third_project.repository;

import com.third_project.third_project.entity.MemberRankingView;
import com.third_project.third_project.entity.TripleRankingView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripleRankingViewRepository extends JpaRepository<TripleRankingView, Long> {
    List<TripleRankingView> findByEtSeq(Long EtSeq);
}
