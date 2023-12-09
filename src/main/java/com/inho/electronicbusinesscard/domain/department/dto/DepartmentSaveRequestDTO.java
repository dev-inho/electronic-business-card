package com.inho.electronicbusinesscard.domain.department.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 소속 지회 생성을 위한 요청 DTO
 */
@Data
public class DepartmentSaveRequestDTO {

    /**
     * 소속 지회 코드
     */
    @NotEmpty(message = "소속 지회 코드는 필수입니다.")
    private String code;

    /**
     * 소속 지회명
     */
    @NotEmpty(message = "소속 지회명은 필수입니다.")
    private String name;

    /**
     * 등록 및 수정하려고 하는 사람의 이름
     */
    private String userId;


    /**
     * DepartmentRequestDTO -> DepartmentDTO
     * @return DepartmentDTO
     */
    public DepartmentDTO toDTO() {
        return DepartmentDTO.builder()
                .code(code)
                .name(name)
                .createdId(userId)
                .updatedId(userId)
                .build();
    }

}
