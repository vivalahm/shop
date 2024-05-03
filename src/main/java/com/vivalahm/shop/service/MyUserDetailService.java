package com.vivalahm.shop.service;

import com.vivalahm.shop.entity.Member;
import com.vivalahm.shop.entity.ShopUserDetails;
import com.vivalahm.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public ShopUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserName(username);
        if(member == null){
            throw new UsernameNotFoundException("username is invalid");
        }
        if(member.getIsAdministrator().equals('Y')){
            return new ShopUserDetails(member.getId(), member.getUserName(), member.getPassword(), member.getDisplayName(), Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }
        return new ShopUserDetails(member.getId(), member.getUserName(), member.getPassword(),member.getDisplayName(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }

}
