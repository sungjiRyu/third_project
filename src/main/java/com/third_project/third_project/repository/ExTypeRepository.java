package com.third_project.third_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.third_project.third_project.entity.ExTypeEntity;
import com.third_project.third_project.main.vo.response.GetExVO;

public interface ExTypeRepository extends JpaRepository<ExTypeEntity,Long> {
  
  public ExTypeEntity findByEtTimeType(Long etTimeType);
  public ExTypeEntity findByEtSeq(Long etSeq);
  // et_es_seq가 4인 운동들만 선택(개인측정용 운동)
  public List<GetExVO> findByEtEsSeq(Long etEsSeq);
}
