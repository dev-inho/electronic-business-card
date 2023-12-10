package com.inho.electronicbusinesscard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inho.electronicbusinesscard.config.filter.JwtFilter;
import com.inho.electronicbusinesscard.config.handler.TokenProvider;
import com.inho.electronicbusinesscard.domain.common.CommonResponseDTO;
import com.inho.electronicbusinesscard.domain.user.dto.SignInRequestDTO;
import com.inho.electronicbusinesscard.domain.user.dto.SignUpRequestDTO;
import com.inho.electronicbusinesscard.domain.user.dto.TokenResponseDTO;
import com.inho.electronicbusinesscard.domain.user.dto.UserDTO;
import com.inho.electronicbusinesscard.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserService userService;

    private final ObjectMapper objectMapper;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponseDTO> authorize(@Valid @RequestBody SignInRequestDTO loginDto) {

        // LoginDto를 이용해 username과 password를 받고 UsernamePasswordAuthenticationToken을 생성합니다.
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDto.getUserId(), loginDto.getPassword());

        // authenticationToken을 이용해서 authentication 객체를 생성하기 위해 authenticate 메서드가 실행될 때,
        // CustomUserDetailsService 에서 구현한 loadUserByUsername 메서드가 실행되고 최종적으로 Authentication 객체가 생성됩니다.
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);

        //  생성된 Authentication 객체를 SecurityContext에 저장합니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //  Authentication 객체를  createToken 메서드를 통해  JWT Token을 생성합니다.
        String jwt = tokenProvider.createToken(authentication);


        HttpHeaders httpHeaders = new HttpHeaders();

        // 생성된 Jwt 토큰을 Response Header에 넣어줍니다.
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        // TokenDto 를 이용해 ResponseBody 에도 넣어 리턴합니다.
        return new ResponseEntity<>(new TokenResponseDTO(jwt), httpHeaders, HttpStatus.OK);
    }

    /**
     * 회원가입
      * @param signUpRequestDTO
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDTO> signup(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) throws Exception {

        userService.signup(signUpRequestDTO);

        return ResponseEntity
                .ok()
                .body(CommonResponseDTO.builder()
                        .code(HttpStatus.OK.value())
                        .msg("회원가입을 성공하였습니다.")
                        .res(true)
                        .build());
    }

    /**
     * 사용자, 권한 정보를 가져오기
     * @return CommonResponseDTO
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')") // 두 권한을 호출할 수 있는 API
    public ResponseEntity<CommonResponseDTO> getMyUserInfo() {
        List<Map<String, Object>> results = new ArrayList<>();
        UserDTO userDTO = userService.getMyUserWithAuthorities()
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        results.add(objectMapper.convertValue(userDTO, Map.class));

        return ResponseEntity
                .ok()
                .body(CommonResponseDTO.builder()
                        .code(HttpStatus.OK.value())
                        .data(results)
                        .res(true)
                        .build());
    }

    /**
     * 사용자, 권한 정보를 가져오기
     * @param userId
     * @return CommonResponseDTO
     */
    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 호출할 수 있는 API
    public ResponseEntity<CommonResponseDTO> getUserInfo(@PathVariable String userId) {
        List<Map<String, Object>> results = new ArrayList<>();
        UserDTO userDTO = userService.getUserWithAuthorities(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        results.add(objectMapper.convertValue(userDTO, Map.class));

        return ResponseEntity
                .ok()
                .body(CommonResponseDTO.builder()
                        .code(HttpStatus.OK.value())
                        .data(results)
                        .res(true)
                        .build());
    }

}
