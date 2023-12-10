package com.inho.electronicbusinesscard.repository;

import com.inho.electronicbusinesscard.domain.user.vo.UserVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@ExtendWith(SpringExtension.class)
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    @DisplayName("사용자 아이디로 조회")
    void findByUserId() {
        Optional<UserVO> test = userMapper.findByUserId("test");

        System.out.println(test.isPresent());

    }
}