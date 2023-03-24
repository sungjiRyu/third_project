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
import com.third_project.third_project.main.vo.response.GetDetailLevelExVO;
import com.third_project.third_project.main.vo.response.GetLevelExVO;
import com.third_project.third_project.repository.ExImgRepository;
import com.third_project.third_project.repository.ExTypeRepository;
import com.third_project.third_project.repository.MemberInfoRepository;

@Service
public class LevelExService {
    // 회원 seq 입력받아서
    // 운동 정보랑 이미지, 타입, 레벨 출력
    // 리스트로 레벨에따른 모든운동 출력
    @Autowired ExTypeRepository exTypeRepo;
    @Autowired MemberInfoRepository memberInfoRepo;
    @Autowired ExImgRepository exImgRepo;

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
            // etName = 걷기(15), 줄넘기(12), 달리기(13), 계단오르기(14)
            Long eimgSeq = null;
          
            if (exTypeRepo != null) {
                for(ExTypeEntity exList : exTypeEntity){
                    if(exList.getEtName().equals("달리기"))             eimgSeq = Long.valueOf(13);
                    if(exList.getEtName().equals("줄넘기"))             eimgSeq = Long.valueOf(12);
                    if(exList.getEtName().equals("걷기"))               eimgSeq = Long.valueOf(15);
                    if(exList.getEtName().equals("계단오르기"))          eimgSeq = Long.valueOf(14);

                    if(exList.getEtName().equals("푸쉬업"))             eimgSeq = Long.valueOf(21);
                    if(exList.getEtName().equals("스쿼트"))             eimgSeq = Long.valueOf(22);
                    if(exList.getEtName().equals("윗몸"))               eimgSeq = Long.valueOf(23);
                    if(exList.getEtName().equals("플랭크"))          eimgSeq = Long.valueOf(20);

                    ExImgEntity img = exImgRepo.findByEimgSeq(eimgSeq);
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

    // 레벨별 운동 상세조회
    // 상세이미지, 운동종목seq, 상세설명 
    public GetDetailLevelExVO detailLevelEx(Long miSeq, Long etSeq){
        GetDetailLevelExVO detailInfo = null;
        ExTypeEntity exList = exTypeRepo.findByEtSeq(etSeq);
        MemberInfoEntity memberInfo = memberInfoRepo.findByMiSeq(miSeq);

        if(memberInfo == null)
        throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("로그인을 해주세요")));
        if(exList == null)
        throw new ExceptionHendler(ErrorResponse.of(HttpStatus.BAD_REQUEST,String.format("존재하지않는 운동종목입니다.")));

        Long eimgSeq = null;

        if(exList.getEtName().equals("달리기"))                  eimgSeq = Long.valueOf(26);
        else if(exList.getEtName().equals("줄넘기"))             eimgSeq = Long.valueOf(25);
        else if(exList.getEtName().equals("걷기"))               eimgSeq = Long.valueOf(27);
        else if(exList.getEtName().equals("계단오르기"))          eimgSeq = Long.valueOf(24);
        else if(exList.getEtName().equals("푸쉬업"))             eimgSeq = Long.valueOf(29);
        else if(exList.getEtName().equals("스쿼트"))             eimgSeq = Long.valueOf(30);
        else if(exList.getEtName().equals("윗몸"))               eimgSeq = Long.valueOf(31);
        else if(exList.getEtName().equals("플랭크"))             eimgSeq = Long.valueOf(28);

        ExImgEntity img = exImgRepo.findByEimgSeq(eimgSeq);

        
        detailInfo = GetDetailLevelExVO.builder()
        .etSeq(exList.getEtSeq())
        .etDetail(exList.getEtDetail())
        .url(img.getEimgUrl())
        .build();
       
        return detailInfo;
        

    }
}
