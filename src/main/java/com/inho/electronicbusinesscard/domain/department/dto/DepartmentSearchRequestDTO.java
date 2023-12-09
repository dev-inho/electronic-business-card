package com.inho.electronicbusinesscard.domain.department.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 소속 지회 검색을 위한 DTO
 */
@Data
public class DepartmentSearchRequestDTO {

    /**
     * 소속 지회 코드
     */
    @NotEmpty(message = "소속 지회 코드를 입력해주세요.")
    @Size(max = 10, message = "소속 지회 코드는 10글자를 넘길 수 없습니다.")
    private String code;

    /**
     * DepartmentSearchRequestDTO -> DepartmentDTO
     * @return DepartmentDTO
     */
    public DepartmentDTO toDTO() {
        return DepartmentDTO.builder()
                .code(code)
                .build();
    }

}
