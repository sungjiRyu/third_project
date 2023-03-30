package com.third_project.third_project.repository;

import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.entity.MemberWeightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberWeightRepository extends JpaRepository<MemberWeightEntity, Long> {
    List<MemberWeightEntity> findByMember(MemberInfoEntity member);
}
