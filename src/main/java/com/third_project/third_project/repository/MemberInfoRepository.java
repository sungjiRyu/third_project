package com.third_project.third_project.repository;


import com.third_project.third_project.entity.MemberInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepository extends JpaRepository<MemberInfoEntity, Long> {
    MemberInfoEntity findByMiSeq(Long miSeq);
    public Integer countByMiNickname(String nickname);
    public void deleteByMiSeq(Long seq);

    public MemberInfoEntity findByMiPwd(String pwd);



    public MemberInfoEntity findByMiId(String id);
    public MemberInfoEntity findByMiIdAndMiPwd(String id, String pwd);
    public Integer countByMiId(String id);
    public Page<MemberInfoEntity> findByMiIdContains(String id, Pageable pageable);






}
