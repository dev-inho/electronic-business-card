package com.inho.electronicbusinesscard.service;

import com.inho.electronicbusinesscard.domain.department.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {

    /**
     * 부서 저장
     */
    void add();

    /**
     * 부서 수정
     */
    void modify();

    /**
     * 전체 조회
     * @return List<DepartmentDTO>
     */
    List<DepartmentDTO> findAll();

    /**
     * 단건 조회
     * @return DepartmentDTO
     */
    DepartmentDTO findById();
}
