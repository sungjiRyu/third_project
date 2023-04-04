package com.third_project.third_project.Detail.vo;

import com.third_project.third_project.entity.IndividualScoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SumScoreVO {
//    private IndividualScoreEntity score;
    private List<IsSumScoreVO> total;
}
