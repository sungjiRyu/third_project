package com.third_project.third_project.repository;


import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.third_project.third_project.entity.ExTypeEntity;

public interface ExTypeRepository extends JpaRepository<ExTypeEntity,Long> {
  ExTypeEntity findByEtSeq (Long etSeq);
  List<ExTypeEntity> findByEtName (String etName);

  Page<ExTypeEntity> findByEtNameContains (String etName, Pageable pageable);
}
