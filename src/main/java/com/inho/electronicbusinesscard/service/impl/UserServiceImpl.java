package com.inho.electronicbusinesscard.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inho.electronicbusinesscard.domain.authority.vo.AuthorityVO;
import com.inho.electronicbusinesscard.domain.user.dto.SignUpRequestDTO;
import com.inho.electronicbusinesscard.domain.user.dto.UserDTO;
import com.inho.electronicbusinesscard.domain.user.exception.DuplicateUserException;
import com.inho.electronicbusinesscard.domain.user.vo.UserVO;
import com.inho.electronicbusinesscard.repository.AuthorityMapper;
import com.inho.electronicbusinesscard.repository.UserMapper;
import com.inho.electronicbusinesscard.service.UserService;
import com.inho.electronicbusinesscard.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;

    private final AuthorityMapper authorityMapper;

    private final PasswordEncoder passwordEncoder;

    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public UserDTO signup(SignUpRequestDTO signUpRequestDTO) throws Exception {

        // UserDto의 username을 이용해 DB에 존재하는지 확인
        if (userMapper.findByUserId(signUpRequestDTO.getUserId()).isPresent()) {
            throw new DuplicateUserException();
        }

        // DB에 존재하지 않으면 권한정보 생성
        AuthorityVO authority = AuthorityVO.builder().authorityName("USER").build();

        // 권한정보를 포함한 User 정보를 생성
        UserVO userVO = UserVO.builder()
                .userId(signUpRequestDTO.getUserId())
                .userName(signUpRequestDTO.getUserName())
                .password(passwordEncoder.encode(signUpRequestDTO.getPassword()))
                .phoneNumber(signUpRequestDTO.getPhoneNumber())
                .authorities(Collections.singleton(authority))
                .isActivated(true)
                .build();

        // 저장
        userMapper.add(objectMapper.convertValue(userVO, Map.class));

        UserDTO userDTO = userMapper.findByUserId(userVO.getUserId())
                .map(UserVO::toDTO)
                .orElseThrow(() -> new UserPrincipalNotFoundException("사용자를 찾을 수 없습니다."));

        // 권한 저장
        Map<String, Object> authorityInfo = new HashMap<>();
        authorityInfo.put("userIdx", userDTO.getUserIdx());
        authorityInfo.put("authorityName", authority.getAuthorityName());

        authorityMapper.add(authorityInfo);

        // 최종 설정한 User 정보를 DB에 저장
        return userDTO;
    }

    // 유저, 권한정보를 가져오는 메서드 1    // username을 기준으로 정보를 가져옴
    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserWithAuthorities(String userId) {
        return userMapper.findByUserId(userId)
                .map(UserVO::toDTO);
    }

    // 유저, 권한정보를 가져오는 메서드 2    // SecurityContext에 저장된 username 정보만 가져옴
    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(userMapper::findByUserId)
                .map(UserVO::toDTO);
    }
}
