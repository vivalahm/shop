package com.vivalahm.shop.service;

import com.vivalahm.shop.entity.Member;
import com.vivalahm.shop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import lombok.Setter;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
@Setter
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public ResponseEntity<String> join(String userName, String password, String displayName) {
        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{3,15}$");
        Matcher userNameMatcher = userNamePattern.matcher(userName);
        if (!userNameMatcher.matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("userName is invalid");
        }

        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z]{3,})(?=.*\\d{3,})(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,15}$");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if (!passwordMatcher.matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("password is invalid");
        }

        Pattern displayNamePattern = Pattern.compile("^.{2,10}$");
        Matcher displayNameMatcher = displayNamePattern.matcher(displayName);
        if (!displayNameMatcher.matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("displayName is invalid");
        }

        var isValid = isValidUserName(userName);
        if(isValid == 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용자가 이미 존재 합니다.");
        }

        try {
            Member member = new Member();
            member.setUserName(userName);
            var EncryptedPassword = passwordEncoder.encode(password);
            member.setPassword(EncryptedPassword);
            member.setDisplayName(displayName);
            Member savedMember = memberRepository.save(member);
            return ResponseEntity.ok("Member saved successfully");
        } catch (DataAccessException e) {
            // 데이터 저장 중 문제가 발생한 경우
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save member");
        }
    }

    public int isValidUserName(String userName){
        Member member = memberRepository.findByUserName(userName);
        if(member == null){
            return 0;
        }
        return 1;
    }
}
