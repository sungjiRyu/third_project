package com.third_project.third_project.Detail.vo;

import com.third_project.third_project.entity.IndividualScoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SumScoreDateVO {
    private Boolean status;
    private String message;
    private HttpStatus code;
    private Integer score;
}
