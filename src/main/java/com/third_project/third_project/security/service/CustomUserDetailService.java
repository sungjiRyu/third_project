package com.third_project.third_project.security.service;

import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberInfoRepository miRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return createUserDetails(miRepo.findByMiId(username));
    }
    public UserDetails createUserDetails(MemberInfoEntity member) {
        return User.builder()
                .username(member.getMiId())
                .password(passwordEncoder.encode(member.getMiPwd()))
                .roles(member.getMiRole())
                .build();
    }
}
