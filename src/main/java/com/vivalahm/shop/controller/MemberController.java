package com.vivalahm.shop.controller;

import com.vivalahm.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/register")
    public String register() {
        return "member/signUp";
    }

    @PostMapping("/signUp")
    public String join(String userName, String password, String displayName) {
        memberService.join(userName, password, displayName);
        return "redirect:/index";
    }
}
