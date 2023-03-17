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
@Table(name = "ex_level")
public class ExLevelEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "레벨 seq")
    @Column(name = "level_seq")    private Long levelSeq;
    @Schema(description = "레벨 (1.1레벨 / 2.2레벨 /3.3레벨)")
    @Column(name = "level_type")    private String levelType;

}
