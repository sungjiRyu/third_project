package com.third_project.third_project.repository;

import com.third_project.third_project.entity.ExVideoEntity;
import com.third_project.third_project.main.vo.response.GetVideoVO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExVideoRepository extends JpaRepository<ExVideoEntity, Long> {
    public ExVideoEntity findByEvSeq(Long evEtSeq);
}
