package com.vivalahm.shop.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class ShopUserDetails extends User {
    private String displayName;
    private Long id;

    public ShopUserDetails(Long id, String username, String password, String displayName, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.displayName = displayName;

    }
}