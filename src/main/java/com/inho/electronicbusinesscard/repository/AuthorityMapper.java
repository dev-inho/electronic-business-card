package com.inho.electronicbusinesscard.repository;

import com.inho.electronicbusinesscard.domain.authority.vo.AuthorityVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AuthorityMapper {

    /**
     * 사용자 PK별 권한 조회
     * @param inputs
     * @return
     */
    List<AuthorityVO> findByUserId(Map<String, Object> inputs);

    /**
     * 권한 추가
     * @param inputs
     */
    void add(Map<String, Object> inputs);

}
