package com.third_project.third_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.third_project.third_project.entity.ExTypeEntity;

public interface ExTypeRepository extends JpaRepository<ExTypeEntity,Long> {
  
}
