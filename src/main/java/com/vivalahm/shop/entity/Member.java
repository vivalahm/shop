package com.vivalahm.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name" , nullable = false, unique = true)
    private String userName;

    @Column(name = "member_password" , nullable = false)
    private String pasword;

    @Column(name = "member_display_name" , nullable = false)
    private String displayName;


}
