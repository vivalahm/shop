package com.vivalahm.shop.common.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests((authorizeRequests) ->
                authorizeRequests
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/register").permitAll()
                        //permit css, js, img
                    .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                    .anyRequest().authenticated()
            );
        http.formLogin((formLogin) ->
                formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/list", true)
                        .failureUrl("/login?error")
        );
        http.logout((logout) ->
            logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
        );
        return http.build();
    }
}
