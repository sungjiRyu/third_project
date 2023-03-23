package com.third_project.third_project.game.vo;

import com.third_project.third_project.entity.GameScoreEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsertGameStatisticeVO {
    MemberInfoEntity member;
    GameScoreEntity game;
    private String percent;
}
