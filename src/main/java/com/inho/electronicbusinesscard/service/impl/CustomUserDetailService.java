package com.inho.electronicbusinesscard.service.impl;

import com.inho.electronicbusinesscard.domain.user.vo.UserVO;
import com.inho.electronicbusinesscard.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("UserDetailService")
public class CustomUserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    /**
     * 아이디로 사용자 조회
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.findByUserId(username)
                .map(user -> createUser(username, user))
                .orElseThrow(() -> new UsernameNotFoundException(username + " 사용자를 찾을 수 없습니다."));
    }

    /**
     * 권한에 따른 사용자 생성
     * @param username
     * @param user
     * @return
     */
    private org.springframework.security.core.userdetails.User createUser(String username,
                                                                          UserVO user) {
        // DB 에서 가져온 유저가 활성화 상태가 아니라면
        if (!user.isActivated()) {
            throw new RuntimeException(username+ "-> 활성화되어 있지 않습니다.");
        }
        // 해당 유저가 활성화 상태라면
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream() // getAuthorities() : 유저의 권한정보
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName())) //
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUserId(),  // 유저명
                user.getPassword(),  // 비밀번호를 가진
                grantedAuthorities); // 유저 객체를 리턴
    }

}
