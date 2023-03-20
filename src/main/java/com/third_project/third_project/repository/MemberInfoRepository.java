package com.third_project.third_project.repository;
import java.util.List;
import com.third_project.third_project.entity.MemberInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepository extends JpaRepository<MemberInfoEntity, Long> {
    MemberInfoEntity findByMiSeq(Long miSeq);
    List<MemberInfoEntity> findAllByMiSeq(Long miSeq);
}
