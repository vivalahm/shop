package com.vivalahm.shop.controller;

import com.vivalahm.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/register")
    public String register() {
        return "member/signUp";
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> join(String userName, String password, String displayName) {
        try {
            System.out.println("여기 타나?");
            ResponseEntity<String> responseEntity = null;
            responseEntity= memberService.join(userName, password, displayName);
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth){
        log.info("auth: {}", auth);
        log.info("auth.isAuthenticated(): {}", auth.isAuthenticated());
        log.info("auth.isUser(): {}", auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
        if(auth.isAuthenticated()){
            return "member/myPage";
        }else{
            return "redirect:/login";
        }
    }

}
