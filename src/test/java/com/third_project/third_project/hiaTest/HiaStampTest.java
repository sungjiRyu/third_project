package com.third_project.third_project.hiaTest;

import com.third_project.third_project.entity.*;
import com.third_project.third_project.game.vo.*;
import com.third_project.third_project.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class HiaStampTest {
    @Autowired
    GameScoreRepository gsRepo;
    @Autowired
    StampInfoRepository stampRepo;
    @Autowired
    MemberInfoRepository mRepo;
    @Autowired
    GoodsInfoRepository goodsRepo;
    @Autowired
    MemberGoodsRepository mgRepo;

    @Test
    public void putStampAvailable(){
        List<GameScoreEntity> game = gsRepo.findWeeklyRanking(1L);
        List<WeeklyRankingVO> ranking = gsRepo.findRanking(1L);
        List<StampInfoEntity> stamp = new ArrayList<>();

        if(game.isEmpty()){
            BasicResponseVO response = BasicResponseVO.builder()
                    .status(false)
                    .message("조회된 게임 성적이 없습니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            System.out.println(response.getStatus());
            System.out.println(response.getMessage());
            System.out.println(response.getCode());
        }
        for(int i=0; i<game.size(); i++) {
            StampInfoEntity entity = stampRepo.findByMember(game.get(i).getMember());
            if (entity == null) {
                StampInfoEntity newEntity = StampInfoEntity.builder()
                        .stampAva(0)
                        .stampTotal(20)
                        .stampUse(0)
                        .member(game.get(i).getMember())
                        .build();
                stampRepo.save(newEntity);
                stamp.add(newEntity);
            }
            stamp.add(entity);
        }
        for(int i = 0; i<stamp.size(); i++){
            StampInfoEntity entity = stamp.get(i);
            if(ranking.get(i).getRanking() == 1 || ranking.get(i).getRanking() == 2 || ranking.get(i).getRanking() == 3){
                entity.ChangeStampAva(2);
                stampRepo.save(entity);
            }
            if(game.get(i).getGsPercent() >= 0.0 && game.get(i).getGsPercent() <= 10.0){
                entity.ChangeStampAva(3);
                stampRepo.save(entity);
            }
            else if(game.get(i).getGsPercent() > 10.0 && game.get(i).getGsPercent() <= 30.0){
                entity.ChangeStampAva(2);
                stampRepo.save(entity);
            }
            else {
                entity.ChangeStampAva(1);
                stampRepo.save(entity);
            }
        }
        BasicResponseVO response = BasicResponseVO.builder()
                .status(true)
                .message("스탬프 기회 입력 완료")
                .code(HttpStatus.OK)
                .build();
        System.out.println(response.getStatus());
        System.out.println(response.getMessage());
        System.out.println(response.getCode());
    }

    public void patchStampUse() {
        MemberInfoEntity member = mRepo.findByMiSeq(1L);
        StampInfoEntity entity = stampRepo.findByMember(member);
        MemberGoodsEntity mg = new MemberGoodsEntity();
        Integer total = entity.getStampTotal();
        Integer ava = entity.getStampAva();
        Integer use = entity.getStampUse();
        if (entity == null) {
            StampResponseVO response = StampResponseVO.builder()
                    .status(false)
                    .message("조회된 회원 정보가 없습니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            System.out.println(response.getStatus());
            System.out.println(response.getMessage());
            System.out.println(response.getCode());
        } else if (entity.getStampAva() == 0) {
            StampResponseVO response = StampResponseVO.builder()
                    .status(false)
                    .message("사용 가능 횟수가 0 입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            System.out.println(response.getStatus());
            System.out.println(response.getMessage());
            System.out.println(response.getCode());
        } else {
            ava += -1;
            use += 1;
            entity.setStampAva(ava);
            entity.setStampUse(use);
            stampRepo.save(entity);
            if (total == use) {
                entity.setStampUse(0);
                stampRepo.save(entity);
            }
            if (use == 5) {
                GoodsInfoEntity goods = goodsRepo.findByGoodsSeq(1L);
                StampResponseVO response = StampResponseVO.builder()
                        .status(true)
                        .message("상품을 획득 하였습니다!!")
                        .code(HttpStatus.ACCEPTED)
                        .goods(goods.getGoodsName() + "이(가) 상품 목록에 담겼습니다.")
                        .build();
                mg.setMember(member);
                mg.setGoods(goods);
                mgRepo.save(mg);
                System.out.println(response.getStatus());
                System.out.println(response.getMessage());
                System.out.println(response.getCode());
            }
            if (use == 5) {
                GoodsInfoEntity goods = goodsRepo.findByGoodsSeq(1L);
                StampResponseVO response = StampResponseVO.builder()
                        .status(true)
                        .message("상품을 획득 하였습니다!!")
                        .code(HttpStatus.ACCEPTED)
                        .goods(goods.getGoodsName() + "이(가) 상품 목록에 담겼습니다.")
                        .build();
                mg.setMember(member);
                mg.setGoods(goods);
                mgRepo.save(mg);
                System.out.println(response.getStatus());
                System.out.println(response.getMessage());
                System.out.println(response.getCode());
            }
            StampResponseVO response = StampResponseVO.builder()
                    .status(true)
                    .message("스텀프에 도장이 찍혔습니다.")
                    .code(HttpStatus.ACCEPTED)
                    .build();
            System.out.println(response.getStatus());
            System.out.println(response.getMessage());
            System.out.println(response.getCode());
        }
    }

    @Test
    public void getMemberGoods(){
        MemberInfoEntity member = mRepo.findByMiSeq(1L);
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
            System.out.println(response.getStatus());
            System.out.println(response.getMessage());
            System.out.println(response.getCode());
        }
        GoodsResponseVO response = GoodsResponseVO.builder()
                .status(true)
                .message("조회된 회원의 보유 상품 정보 조회!!")
                .code(HttpStatus.OK)
                .list(goods)
                .build();
        System.out.println(response.getStatus());
        System.out.println(response.getMessage());
        System.out.println(response.getCode());
    }
}
