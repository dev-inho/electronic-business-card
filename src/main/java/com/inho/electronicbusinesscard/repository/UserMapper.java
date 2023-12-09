package com.inho.electronicbusinesscard.repository;

import com.inho.electronicbusinesscard.domain.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 사용자 정보 조회
     * @return 조회된 사용자 수
     */
    List<UserVO> findAll(Map<String, Object> inputs);

    /**
     * 사용자 정보 생성
     * @param inputs
     * @return 영향을 준 쿼리 수
     */
    int add(Map<String, Object> inputs);

    /**
     * 사용자 정보 수정
     *
     * @param inputs
     * @return 영향을 준 쿼리 수
     */
    int modify(Map<String, Object> inputs);
}
