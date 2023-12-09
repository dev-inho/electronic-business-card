package com.inho.electronicbusinesscard.domain.department.dto;

import lombok.Data;

/**
 * 소속 지회 생성을 위한 요청 DTO
 */
@Data
public class DepartmentRequestDTO {

    /**
     * 소속 지회 코드
     */
    private String code;

    /**
     * 소속 지회명
     */
    private String name;

    /**
     * DepartmentRequestDTO -> DepartmentDTO
     * @return DepartmentDTO
     */
    public DepartmentDTO toDTO() {
        return DepartmentDTO.builder()
                .code(code)
                .name(name)
                .build();
    }

}
