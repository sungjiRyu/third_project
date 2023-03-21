package com.third_project.third_project.repository;

import com.third_project.third_project.entity.ExImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExImgRepository extends JpaRepository<ExImgEntity, Long> {
  ExImgEntity findByEimgUrl (String eimgUrl);
  ExImgEntity findByEimgSeq (Long eimgSeq);
    public ExImgEntity findByEimgUrlEquals(String eimgUrl);
}
