package com.inho.electronicbusinesscard.config.handler;

import com.inho.electronicbusinesscard.domain.authority.vo.AuthorityVO;
import com.inho.electronicbusinesscard.domain.user.vo.UserVO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private final String secret;

    private final long tokenValidityInMilliseconds;

    private Key key;

    /**
     * constructor
     * @param secret 암호화키
     * @param tokenValidityInMilliseconds 토큰 유효성 밀리초
     */
    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds) {

        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds * 1000;
    }

    /**
     * 랜덤 암호화 키
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    /**
     * 객체의 권한 정보를 이용해 토큰 생성
     * @param authentication 인증 정보
     * @return Token
     */
    public String createToken(Authentication authentication) {

        /* authentication 초기화 */
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        /* application.yml 에서 정의한 token 만료 시간을 호출 시간 설정 */
        Date validity = new Date(now + this.tokenValidityInMilliseconds);


        /* Jwt 토큰을 생성 후 리턴 */
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {

        // 파라미터로 받아온 token을 이용해서 Claim 생성
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        // Claim에서 권한정보들을 빼냄
        Collection<? extends GrantedAuthority> grantedAuthorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet());

        Set<AuthorityVO> authorities = grantedAuthorities.stream()
                .map(grantedAuthority -> new AuthorityVO(grantedAuthority.getAuthority())) // 이 부분은 AuthorityVO의 생성자나 팩토리 메서드에 따라 달라질 수 있습니다.
                .collect(Collectors.toSet());

        // 빼낸 권한정보를 이용해 유저를 생성
        UserVO principal = new UserVO(claims.getSubject(), "", authorities);

        // 유저 객체에 권한 설정
        principal.setAuthorities((Set<AuthorityVO>) authorities);

        // 유저정보, 토큰, 권한정보를 가진 Authentication 객체 리턴
        return new UsernamePasswordAuthenticationToken(principal, token, grantedAuthorities);
    }

    /**
     * 토큰 유효성 검사
     * @param token
     * @return
     */
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e){
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e){
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e){
            log.info("JWT 토큰이 잘못되었습니다.");
        }

        return false;
    }
}
