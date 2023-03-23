package com.third_project.third_project.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.third_project.third_project.entity.ExImgEntity;
import com.third_project.third_project.entity.ExTypeEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.main.vo.ErrorResponse;
import com.third_project.third_project.main.vo.exceptionHendler.ExceptionHendler;
import com.third_project.third_project.main.vo.response.GetLevelExVO;
import com.third_project.third_project.repository.ExTypeRepository;
import com.third_project.third_project.repository.MemberInfoRepository;

@Service
public class LevelExService {
    // 회원 seq 입력받아서
    // 운동 정보랑 이미지, 타입, 레벨 출력
    // 리스트로 레벨에따른 모든운동 출력
    @Autowired ExTypeRepository exTypeRepo;
    @Autowired MemberInfoRepository memberInfoRepo;

    public List<GetLevelExVO> levelExList(Long miSeq){
        // miSeq로 회원정보검색
        // 성별(mi_gi_seq 1=남/2=여)과 타입(exStatus 1=다이어트/2=웨이트) 을 가져와서
        // 해당하는 운동 정보 출력 
        MemberInfoEntity memberInfoEntity = memberInfoRepo.findByMiSeq(miSeq);
        List<ExTypeEntity> exTypeEntity = new ArrayList();
        List<GetLevelExVO> list = new ArrayList<>();
        if(memberInfoEntity == null){
            throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("해당하는 회원이 없습니다.")));
        }
        else{
            // 로그인한 회원의 성별과 타입에 맞는 운동종목을 불러옴 
            exTypeEntity = exTypeRepo.findByGenAndEtEsSeq(memberInfoEntity.getGen(), memberInfoEntity.getExStatus().getEsSeq());
            if (exTypeRepo != null) {
                for(ExTypeEntity exList : exTypeEntity){
                    ExImgEntity img = exList.getImg();
                    if (img != null) {
                        list.add(GetLevelExVO.builder()
                            .etSeq(exList.getEtSeq())
                            .etName(exList.getEtName())
                            .etDetail(exList.getEtDetail())
                            .url(img.getEimgUrl())
                            .build());
                    }
                }
            }
        }
        return list;
    }

}
