package com.inho.electronicbusinesscard.repository;

import com.inho.electronicbusinesscard.domain.department.vo.DepartmentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 소속 지회 데이터 영속성
 */
@Mapper
public interface DepartmentMapper {

    /**
     * 소속 지회 생성
     * @param inputs {code: String, name: String}
     * @return 영향을 준 쿼리
     */
    int add(Map<String, Object> inputs);

    /**
     * 소속 지회 수정
     * @param inputs {code: String, name: String}
     * @return 영향을 준 쿼리
     */
    int modify(Map<String, Object> inputs);

    /**
     * 소속 지회 전체 조회
     * @return DepartmentVOs
     */
    List<DepartmentVO> findAll();

    /**
     * 소속 지회 단건 조회
     * @param inputs {code: String, name: String}
     * @return Optional<DepartmentVO>
     */
    Optional<DepartmentVO> findById(Map<String, Object> inputs);

    /**
     * 소속 지회 삭제
     * @param inputs {code: String}
     * @return 영향을 준 쿼리
     */
    int delete(Map<String, Object> inputs);

}
