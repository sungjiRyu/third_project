package com.third_project.third_project.main.vo.response;

import java.util.List;

import com.third_project.third_project.entity.ExTypeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetExListVO {
    public List<ExTypeEntity> excriseList;
    
}
