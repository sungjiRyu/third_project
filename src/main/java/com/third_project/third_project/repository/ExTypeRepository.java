package com.third_project.third_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.third_project.third_project.entity.ExImgEntity;
import com.third_project.third_project.entity.ExLevelEntity;
import com.third_project.third_project.entity.ExTypeEntity;
import com.third_project.third_project.entity.GenInfoEntity;
import com.third_project.third_project.main.vo.response.GetExVO;

public interface ExTypeRepository extends JpaRepository<ExTypeEntity,Long> {
  
  public ExTypeEntity findByEtTimeType(Long etTimeType);
  public ExTypeEntity findByEtSeq(Long etSeq);
  // et_es_seq가 4인 운동들만 선택(개인측정용 운동)
  public List<GetExVO> findByEtEsSeq(Long etEsSeq);
  // 회원유형(다이어터,웨이터/남,여)에 맞는 레벨별 운동 리스트 조회
  // public List<ExTypeEntity> findByMiseq(L)
  public List<ExTypeEntity> findByGenAndEtEsSeqAndLevel(GenInfoEntity gen, Long etEsSeq, ExLevelEntity
  exLevelEntity);
}
