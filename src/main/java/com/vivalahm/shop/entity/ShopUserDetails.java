package com.vivalahm.shop.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ShopUserDetails extends User {
    private String displayName;

    public ShopUserDetails(String username, String password, String displayName, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}