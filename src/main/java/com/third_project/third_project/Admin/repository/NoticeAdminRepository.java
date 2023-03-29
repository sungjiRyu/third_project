package com.third_project.third_project.Admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.third_project.third_project.Admin.entity.NoticeAdminEntity;

public interface NoticeAdminRepository extends JpaRepository<NoticeAdminEntity,Long>  {
      //공지사항 관리자 페이지  리스트 조회
      Page<NoticeAdminEntity> findByGnTitleContains(String gnTitle, Pageable pageable);
}
