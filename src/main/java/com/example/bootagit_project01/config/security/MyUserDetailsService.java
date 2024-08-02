package com.example.bootagit_project01.config.security;


import com.example.bootagit_project01.user.user.entity.User;
import com.example.bootagit_project01.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private MyUserDetailsService myUserDetailsService;

    public void SecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }
    /*
    * [JWT 토큰의 인증 계정 찾기]
    * MyUserDetails 의 메서드를 오버라이딩하여, MemberRepository의 로직으로 인증 계정을 찾음
    *
    * */

    @Override
    public MyUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(this::createUserDetail)
                .orElseThrow(() ->new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    /* CustomUserDetail 형태로 반환
    * 해당 계정 정보가 존재 시, CustomUserDetail의 형태로 리턴
    * @param [User] 찾은 멤버 엔티티 객체
    * @return [CustomUserDetail]
    *  */

    private MyUserDetail createUserDetail(User user){
        Collection<? extends GrantedAuthority> authorities =
                Collections.singleton(new SimpleGrantedAuthority(toString()));
        return new MyUserDetail(
                user.getUserid(),
                user.getPassword(),
                user.getUsername(),
                user.getEmail()
        );
    }

}
