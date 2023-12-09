package com.inho.electronicbusinesscard.service;

import com.inho.electronicbusinesscard.domain.department.dto.DepartmentDTO;
import com.inho.electronicbusinesscard.domain.department.dto.DepartmentSaveRequestDTO;
import com.inho.electronicbusinesscard.domain.department.dto.DepartmentSearchRequestDTO;

import java.util.List;

public interface DepartmentService {

    /**
     * 부서 저장
     * @param departmentSaveRequestDTO 저장하기 위한 DTO
     */
    void add(DepartmentSaveRequestDTO departmentSaveRequestDTO);

    /**
     * 부서 수정
     * @param departmentSaveRequestDTO 수정하기 위한 DTO
     */
    void modify(DepartmentSaveRequestDTO departmentSaveRequestDTO);

    /**
     * 전체 조회
     * @return List<DepartmentDTO>
     */
    List<DepartmentDTO> findAll();

    /**
     * 단건 조회
     * @param departmentSearchRequestDTO 지회 코드로 검색
     * @return DepartmentDTO
     */
    DepartmentDTO findById(DepartmentSearchRequestDTO departmentSearchRequestDTO);

}
