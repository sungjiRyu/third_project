package com.third_project.third_project.repository;

import com.third_project.third_project.entity.CertificationVideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationVideoReposritory extends JpaRepository<CertificationVideoEntity, Long> {
    public CertificationVideoEntity findByCvUrlEquals(String cvUrl);
}
