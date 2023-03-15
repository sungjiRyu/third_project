package com.third_project.third_project.repository;

import com.third_project.third_project.entity.MemberGoodsEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberGoodsRepository extends JpaRepository<MemberGoodsEntity, Long> {
}
