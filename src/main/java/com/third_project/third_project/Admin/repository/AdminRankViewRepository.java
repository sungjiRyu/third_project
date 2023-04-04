package com.third_project.third_project.Admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.third_project.third_project.Admin.entity.AdminRankView;

public interface AdminRankViewRepository extends JpaRepository<AdminRankView,Long> {
  Page<AdminRankView> findByEtNameContains(String keyword, Pageable pageable);
}
