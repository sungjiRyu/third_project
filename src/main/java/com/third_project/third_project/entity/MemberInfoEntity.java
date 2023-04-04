package com.third_project.third_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@DynamicInsert
@Table(name="member_info")
public class MemberInfoEntity implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mi_seq")          private Long   miSeq;
    @Column(name="mi_id")           private String miId;
    @Column(name="mi_pwd")          private String miPwd;
    @Column(name="mi_tall")         private Integer miTall;
    @Column(name="mi_weight")       private Integer miWeight;
    @Column(name="mi_nickname")     private String miNickname;
    @Column(name="mi_role")
    @ColumnDefault("user") private String miRole;
    @Column(name="mi_classnum")
    @ColumnDefault("반 선택") private String miClassNum;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mi_gi_seq")
    @ColumnDefault("1") private GenInfoEntity gen;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mi_es_seq")
    @ColumnDefault("1") private ExStatusEntity exStatus;
    @OneToOne //( cascade = CascadeType.ALL)
    @JoinColumn(name="mi_mimg_seq")
    @ColumnDefault("1") private MemberImgEntity mimg;

    @Override @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(this.miRole));
        return roles;
    }

    @Override @JsonIgnore
    public String getPassword() { return this.miPwd; }
    @Override @JsonIgnore
    public String getUsername() { return this.miId; }
    @Override @JsonIgnore
    public boolean isAccountNonExpired() { return true; }
    @Override @JsonIgnore
    public boolean isAccountNonLocked() { return true; }
    @Override @JsonIgnore
    public boolean isCredentialsNonExpired() { return true; }
    @Override @JsonIgnore
    public boolean isEnabled() { return true; }
}
