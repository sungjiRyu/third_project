package com.third_project.third_project.repository;

import com.third_project.third_project.entity.ExImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExImgRepository extends JpaRepository<ExImgEntity, Long> {
    public ExImgEntity findByEimgUrlEquals(String eimgUrl);
}
