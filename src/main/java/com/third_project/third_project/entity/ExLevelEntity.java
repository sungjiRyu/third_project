package com.third_project.third_project.entity;

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
    @Column(name = "level_seq")    private Long levelSeq;
    @Column(name = "level_type")    private String levelType;

}
