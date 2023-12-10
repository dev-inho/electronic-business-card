package com.inho.electronicbusinesscard.service;

import com.inho.electronicbusinesscard.domain.user.dto.SignUpRequestDTO;
import com.inho.electronicbusinesscard.domain.user.dto.UserDTO;

import java.util.Optional;

public interface UserService {

    /**
     * 회원가입
     * @param signUpRequestDTO
     * @return UserDTO
     * @throws Exception
     */
    UserDTO signup(SignUpRequestDTO signUpRequestDTO) throws Exception;

    /**
     * 사용자, 권한 정보를 가져오는 메서드
     * @param userId
     * @return Optional<UserDTO>
     */
    Optional<UserDTO> getUserWithAuthorities(String userId);

    /**
     * 사용자 권한 정보를 가져오는 메서드
     * SecurityContext에 저장된 username 정보만 가져옴
     * @return Optional<UserDTO>
     */
    Optional<UserDTO> getMyUserWithAuthorities();

}
