package com.vivalahm.shop.controller;

import com.vivalahm.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
        System.out.println(auth);
        System.out.println(auth.getPrincipal());
        System.out.println(auth.getAuthorities());
        if(auth.isAuthenticated()){
            return "member/myPage";
        }else{
            return "redirect:/login";
        }
    }

}
