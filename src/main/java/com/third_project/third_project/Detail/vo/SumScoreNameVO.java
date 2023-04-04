package com.third_project.third_project.Detail.vo;

import com.third_project.third_project.entity.SumScoreNameViewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SumScoreNameVO {
    private Boolean status;
    private String message;
    private HttpStatus code;
    private List<SumScoreNameViewEntity> score;
}
