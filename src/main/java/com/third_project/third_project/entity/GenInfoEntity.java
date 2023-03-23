package com.third_project.third_project.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "gen_info")
public class GenInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "성별번호")
    @Column(name = "gi_seq")   private Long giSeq;
    @Schema(description = "성별정보 (1.남 / 2.여 /3.게임용/ 4. 개인측정용)")
    @Column(name = "gi_status")   private String giStatus;
}
