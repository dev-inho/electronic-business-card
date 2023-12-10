package com.inho.electronicbusinesscard.repository;

import com.inho.electronicbusinesscard.domain.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
import java.util.Optional;

@Mapper
public interface UserMapper {

    /**
     * 사용자 아이디로 조회
     * @param userId 아이디
     * @return Optional<UserVO>
     */
    Optional<UserVO> findByUserId(String userId);

    /**
     * 사용자 고유 IDX로 조회
     * @param userIdx 고유 IDX
     * @return Optional<UserVO>
     */
    Optional<UserVO> findById(long userIdx);

    /**
     * 사용자 등록
     * @param inputs
     */
    void add(Map<String, Object> inputs);

    /**
     * 사용자 수정
     * @param inputs
     * @return 영향을 준 쿼리
     */
    int modify(Map<String, Object> inputs);

}
