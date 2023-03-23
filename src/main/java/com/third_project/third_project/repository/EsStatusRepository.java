package com.third_project.third_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.third_project.third_project.entity.EsStatusEntity;

public interface EsStatusRepository extends JpaRepository<EsStatusEntity,Long> {
  
}
