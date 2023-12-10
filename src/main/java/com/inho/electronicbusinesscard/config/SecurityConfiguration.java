package com.inho.electronicbusinesscard.config;

import com.inho.electronicbusinesscard.config.handler.JwtAccessDeniedHandler;
import com.inho.electronicbusinesscard.config.handler.JwtAuthenticationEntryPoint;
import com.inho.electronicbusinesscard.config.handler.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final TokenProvider tokenProvider;

    /**
     * 스프링 시큐리티 전체 보안
     * @return WebSecurityCustomizer
     */
    @Bean
    public WebSecurityCustomizer configure() {

        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                .requestMatchers(new AntPathRequestMatcher("/favicon.ico"));
    }

    /**
     * HTTP 요청에 대한 보안 규칙
     * @param httpSecurity HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)  // csrf를 disable() 설정
                .exceptionHandling(
                        (handling) ->  // exceptionHandling 시 앞서 정의한 클래스를 추가
                                handling.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                        .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .headers((header) -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                // 세션을 사용하지 않기 STATELESS 설정
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((registry) ->
                        registry.requestMatchers(
                                        new AntPathRequestMatcher("/department", "POST"),
                                        new AntPathRequestMatcher("/department", "PUT")
                                ).hasRole("ADMIN")
                                .requestMatchers(
                                        new AntPathRequestMatcher("/department", "GET"),
                                        new AntPathRequestMatcher("/department/list", "GET"),
                                        new AntPathRequestMatcher("/user/**")
                                ).permitAll()
                                .anyRequest().authenticated()

                )
                .apply(new JwtSecurityConfiguration(tokenProvider));

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
