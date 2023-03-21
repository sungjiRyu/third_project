package com.third_project.third_project.repository;

import com.third_project.third_project.entity.GenInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenInfoRepository extends JpaRepository<GenInfoEntity, Long> {
    public GenInfoEntity findByGiSeq(Long seq);
}
