package com.third_project.third_project.repository;

import com.third_project.third_project.entity.ExTypeEntity;
import com.third_project.third_project.entity.GameScoreEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.entity.MemberRankingView;
import com.third_project.third_project.game.vo.WeeklyRankingVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface GameScoreRepository extends JpaRepository<GameScoreEntity, Long> {
    GameScoreEntity findByMember(MemberInfoEntity member);
//    GameScoreEntity findByMemberRankingView(MemberRankingView mrentity);

    List<GameScoreEntity> findByExType (ExTypeEntity exType);
    GameScoreEntity findByGsTime(LocalTime gsTime);
    List<GameScoreEntity> findAllByOrderByGsTimeAsc();
    List<GameScoreEntity> findByGsRegDt(LocalDate gsRegDt);

    @Query(value = "select *, mi_nickname, mi_classnum, mimg_url ,et_name, ranking from "
    +"(select * from (select *, dense_rank() over(partition by b.et_time_type order by calculated asc) as ranking from "
    +"(select *, (case when b.et_time_type = 1 then (TIME('00:00:00') + a.gs_time) when b.et_time_type = 2 then (TIME('00:00:00') - a.gs_time) end) "
            +"as calculated from game_score a join ex_type b on a.gs_et_seq = b.et_seq) as c) d join member_info e on d.gs_mi_seq = e.mi_seq "
    +"join member_img g on e.mi_mimg_seq = g.mimg_seq) f where week(gs_reg_dt) = week(now())-1 and et_seq = :seq order by ranking ", nativeQuery = true)
       List<GameScoreEntity> findWeeklyRanking(@Param("seq") Long seq );

    @Query(value = "select *, mi_nickname, mi_classnum, mimg_url ,et_name, ranking from "
    +"(select * from (select *, dense_rank() over(partition by b.et_time_type order by calculated asc) as ranking from "
    +"(select *, (case when b.et_time_type = 1 then (TIME('00:00:00') + a.gs_time) when b.et_time_type = 2 then (TIME('00:00:00') - a.gs_time) end) "
            +"as calculated from game_score a join ex_type b on a.gs_et_seq = b.et_seq) as c) d join member_info e on d.gs_mi_seq = e.mi_seq "
    +"join member_img g on e.mi_mimg_seq = g.mimg_seq) f where week(gs_reg_dt) = week(now())-1 and et_seq = :seq order by ranking ", nativeQuery = true)
       List<WeeklyRankingVO> findRanking(@Param("seq") Long seq );
}
