package com.third_project.third_project.repository;

import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.entity.StampInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StampInfoRepository extends JpaRepository<StampInfoEntity, Long> {
    StampInfoEntity findByMember(MemberInfoEntity member);
}
