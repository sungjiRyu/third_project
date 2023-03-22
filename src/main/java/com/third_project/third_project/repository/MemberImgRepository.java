package com.third_project.third_project.repository;

import com.third_project.third_project.entity.MemberImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberImgRepository extends JpaRepository<MemberImgEntity, Long> {
    public MemberImgEntity findByMimgSeq(Long seq);
    public MemberImgEntity findByMimgUrl(String url);

}
