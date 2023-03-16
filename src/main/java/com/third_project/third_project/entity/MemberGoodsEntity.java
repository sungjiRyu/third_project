package com.third_project.third_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mg_mi_seq") private MemberInfoEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mg_goods_seq") private GoodsInfoEntity Goods;
}
