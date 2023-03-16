package com.third_project.third_project.repository;

import com.third_project.third_project.entity.GoodsInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsInfoRepository extends JpaRepository<GoodsInfoEntity, Long> {
    public GoodsInfoEntity findByGoodsSeq(Long goodsSeq);
}
