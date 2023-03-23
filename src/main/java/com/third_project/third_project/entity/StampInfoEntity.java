package com.third_project.third_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="stamp_info")
public class StampInfoEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name="stamp_seq")   private Long stampSeq;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="stamp_mi_seq")  private MemberInfoEntity member;
    @Column(name="stamp_total")  private Integer stampTotal;
    @Column(name="stamp_ava")    private Integer stampAva;
    @Column(name="stamp_use")    private Integer stampUse;

    public void ChangeStampAva(Integer no) {
        this.stampAva += no;
    }
}
