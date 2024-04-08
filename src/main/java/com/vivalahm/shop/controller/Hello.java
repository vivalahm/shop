package com.vivalahm.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
public class Hello {
    @GetMapping("/")
    @ResponseBody
    public String hello(){
        return "안녕하세요.";
    }

    @GetMapping("/about")
    @ResponseBody
    public String about(){
        return "안녕하세요. 저는 Vivalahm입니다.";
    }

    @GetMapping("/date")
    public String date(Model model){
        String date = LocalDateTime.now().toString();
        model.addAttribute("date", date);
        return "Date/index";
    }
}
