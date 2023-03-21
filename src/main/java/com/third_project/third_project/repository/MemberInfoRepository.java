package com.third_project.third_project.repository;
import java.util.List;
import com.third_project.third_project.entity.MemberInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepository extends JpaRepository<MemberInfoEntity, Long> {
    MemberInfoEntity findByMiSeq(Long miSeq);
    public Integer countByMiId(String id);
    public Integer countByMiNickname(String nickname);
    public void deleteByMiSeq(Long seq);



}
