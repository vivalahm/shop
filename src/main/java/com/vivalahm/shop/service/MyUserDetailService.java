package com.vivalahm.shop.service;

import com.vivalahm.shop.entity.Member;
import com.vivalahm.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserName(username);
        if(member == null){
            throw new UsernameNotFoundException("username is invalid");
        }
        return new User(member.getUserName(), member.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }

}
