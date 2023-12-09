package com.inho.electronicbusinesscard.domain.department.vo;

import com.inho.electronicbusinesscard.domain.common.BaseVO;
import com.inho.electronicbusinesscard.domain.department.dto.DepartmentDTO;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString(callSuper = true)
public class DepartmentVO extends BaseVO {

    /**
     * 코드
     */
    private final String code;

    /**
     * 소속 지회 이름
     */
    private final String name;

    public DepartmentVO(String createdId, LocalDateTime createdDt, String updatedId, LocalDateTime updatedDt
                    , String code, String name) {
        super(createdId, createdDt, updatedId, updatedDt);

        this.code = code;
        this.name = name;
    }

    /**
     * DepartmentVO -> DepartmentDTO
     * @return DepartmentDTO
     */
    public DepartmentDTO toDTO() {
        return DepartmentDTO.builder()
                .code(code)
                .name(name)
                .createdDt(super.getCreatedDt())
                .createdId(super.getCreatedId())
                .updatedDt(super.getUpdatedDt())
                .updatedId(super.getUpdatedId())
                .build();
    }

}
