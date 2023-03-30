package com.third_project.third_project.Admin.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotiiceAdminInsertVO {
  
    private String gnTitle;
    private String gnContent;
    private Long gnMiSeq;
    private Long gnEvSeq;
    private Long gnEtSeq;
}
