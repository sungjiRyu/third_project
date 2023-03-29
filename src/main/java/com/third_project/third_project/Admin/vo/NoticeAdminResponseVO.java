package com.third_project.third_project.Admin.vo;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.third_project.third_project.entity.GameNoticeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class NoticeAdminResponseVO {
  private List<GameNoticeEntity> list;
  private HttpStatus code;
  private String message;
  private Boolean status;
}
