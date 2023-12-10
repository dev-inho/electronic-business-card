package com.inho.electronicbusinesscard.config;

import com.inho.electronicbusinesscard.config.filter.JwtFilter;
import com.inho.electronicbusinesscard.config.handler.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtSecurityConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;

    @Override
    public void configure(HttpSecurity http) {

        JwtFilter customFilter = new JwtFilter(tokenProvider);

        // JwtFilter를 이용해 Spring Security 필터에 등록
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
