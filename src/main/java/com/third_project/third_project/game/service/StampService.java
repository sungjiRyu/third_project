package com.third_project.third_project.game.service;

import com.third_project.third_project.entity.GoodsInfoEntity;
import com.third_project.third_project.entity.MemberGoodsEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.entity.StampInfoEntity;
import com.third_project.third_project.game.vo.GoodsResponseVO;
import com.third_project.third_project.game.vo.MemberGoodsResponseVO;
import com.third_project.third_project.game.vo.StampInfoResponseVO;
import com.third_project.third_project.game.vo.StampResponseVO;
import com.third_project.third_project.repository.GoodsInfoRepository;
import com.third_project.third_project.repository.MemberGoodsRepository;
import com.third_project.third_project.repository.MemberInfoRepository;
import com.third_project.third_project.repository.StampInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StampService {
    private final StampInfoRepository sRepo;
    private final MemberInfoRepository mRepo;
    private final GoodsInfoRepository goodsRepo;
    private final MemberGoodsRepository mgRepo;

    // 스텀프 정보 조회 기능
    public StampInfoResponseVO getStampInfo(Long seq){
        MemberInfoEntity member = mRepo.findByMiSeq(seq);
        StampInfoEntity entity = sRepo.findByMember(member);
        if(entity == null){
            StampInfoResponseVO response = StampInfoResponseVO.builder()
                    .status(false)
                    .message("조회된 회원 정보가 없습니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return response;
        }
        StampInfoResponseVO response = StampInfoResponseVO.builder()
                .status(true)
                .message("회원의 스텀프 정보 조회!!")
                .code(HttpStatus.OK)
                .total(entity.getStampTotal())
                .available(entity.getStampAva())
                .use(entity.getStampUse())
                .build();
        return response;
    }

    // 스텀프 사용 시 stamp_ava 와 stamp_use 변경 기능
    public StampResponseVO patchStampUse(Long seq){
        MemberInfoEntity member = mRepo.findByMiSeq(seq);
        StampInfoEntity entity = sRepo.findByMember(member);
        MemberGoodsEntity mg = new MemberGoodsEntity();
        Integer total = entity.getStampTotal();
        Integer ava = entity.getStampAva();
        Integer use = entity.getStampUse();
        if(entity == null){
            StampResponseVO response = StampResponseVO.builder()
                    .status(false)
                    .message("조회된 회원 정보가 없습니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return response;
        }
        else if(entity.getStampAva() == 0){
            StampResponseVO response = StampResponseVO.builder()
                    .status(false)
                    .message("사용 가능 횟수가 0 입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return response;
        }
        else{
            ava += -1;
            use += 1;
            entity.setStampAva(ava);
            entity.setStampUse(use);
            sRepo.save(entity);
            if(total == use){
                entity.setStampUse(0);
                sRepo.save(entity);
            }
            if(use == 5){
                GoodsInfoEntity goods = goodsRepo.findByGoodsSeq(1L);
                StampResponseVO response = StampResponseVO.builder()
                        .status(true)
                        .message("상품을 획득 하였습니다!!")
                        .code(HttpStatus.ACCEPTED)
                        .goods(goods.getGoodsName()+"이(가) 상품 목록에 담겼습니다.")
                        .build();
                mg.setMember(member);
                mg.setGoods(goods);
                mgRepo.save(mg);
                return response;
            }
            else if(use == 10){
                GoodsInfoEntity goods = goodsRepo.findByGoodsSeq(2L);
                StampResponseVO response = StampResponseVO.builder()
                        .status(true)
                        .message("상품을 획득 하였습니다!!")
                        .code(HttpStatus.ACCEPTED)
                        .goods(goods.getGoodsName()+"이(가) 상품 목록에 담겼습니다.")
                        .build();
                mg.setMember(member);
                mg.setGoods(goods);
                mgRepo.save(mg);
                return response;
            }
            else if(use == 15){
                GoodsInfoEntity goods = goodsRepo.findByGoodsSeq(3L);
                StampResponseVO response = StampResponseVO.builder()
                        .status(true)
                        .message("상품을 획득 하였습니다!!")
                        .code(HttpStatus.ACCEPTED)
                        .goods(goods.getGoodsName()+"이(가) 상품 목록에 담겼습니다.")
                        .build();
                mg.setMember(member);
                mg.setGoods(goods);
                mgRepo.save(mg);
                return response;
            }
            else if(use == 20){
                GoodsInfoEntity goods = goodsRepo.findByGoodsSeq(4L);
                StampResponseVO response = StampResponseVO.builder()
                        .status(true)
                        .message("상품을 획득 하였습니다!!")
                        .code(HttpStatus.ACCEPTED)
                        .goods(goods.getGoodsName()+"이(가) 상품 목록에 담겼습니다.")
                        .build();
                mg.setMember(member);
                mg.setGoods(goods);
                mgRepo.save(mg);
                return response;
            }
        }
        StampResponseVO response = StampResponseVO.builder()
                .status(true)
                .message("스텀프에 도장이 찍혔습니다.")
                .code(HttpStatus.ACCEPTED)
                .build();
        return response;
    }

    // 회원이 보유한 상품 정보 조회 기능
    public GoodsResponseVO getMemberGoods(Long seq){
        MemberInfoEntity member = mRepo.findByMiSeq(seq);
        List<MemberGoodsEntity> list = mgRepo.findByMember(member);
        List<MemberGoodsResponseVO> goods = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            MemberGoodsResponseVO vo =MemberGoodsResponseVO.builder()
                    .name(list.get(i).getGoods().getGoodsName())
                    .build();
                    goods.add(vo);
        }

        if(list.isEmpty()){
            GoodsResponseVO response = GoodsResponseVO.builder()
                    .status(false)
                    .message("조회된 회원 정보가 없습니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return response;
        }
            GoodsResponseVO response = GoodsResponseVO.builder()
                    .status(true)
                    .message("조회된 회원의 보유 상품 정보 조회!!")
                    .code(HttpStatus.OK)
                    .list(goods)
                    .build();
            return response;

    }
}
