package com.inho.electronicbusinesscard.config.filter;

import com.inho.electronicbusinesscard.config.handler.TokenProvider;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;

    /**
     * 토큰의 인증정보(Authentication)를 SecurityContext에 저장하는 역할 수행
     * @param servletRequest ServletRequest
     * @param servletResponse ServletResponse
     * @param filterChain FilterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        // Reques에서 토큰을 받아옴
        String jwt = resolveToken(httpServletRequest);

        String requestURI = httpServletRequest.getRequestURI();

        // 받아온 jwt 토큰을 validateToken 메서드로 유효성 검증
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {

            // 토큰이 정상이라면 Authentication 객체를 받아옴
            Authentication authentication = tokenProvider.getAuthentication(jwt);

            // SecurityContext에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Security Context에 '{}' 인증정보를 저장했습니다., uri: {}", authentication.getName(),
                    requestURI);
        } else {
            log.debug("유효한 JWT 토큰이 없습니다, uri : {}", requestURI);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Request Header 에서 토큰 정보를 가져오기 위한 메서드
     * @param request HttpServletRequest
     * @return Token
     */
    private String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
