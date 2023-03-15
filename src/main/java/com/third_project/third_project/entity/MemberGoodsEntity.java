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
@Table(name = "member_goods")
public class MemberGoodsEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mg_seq") private Long mgSeq;
    @Column(name = "mg_mi_seq") private Long MgMiSeq;

    @Column(name = "goods_seq") private Long GoodsSeq;
}
