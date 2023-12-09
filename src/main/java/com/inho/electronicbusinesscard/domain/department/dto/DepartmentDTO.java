package com.inho.electronicbusinesscard.domain.department.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    /**
     * 소속 지회 코드
     */
    private String code;

    /**
     * 소속 지회명
     */
    private String name;

    /**
     * 생성자
     */
    private String createdId;

    /**
     * 생성일
     */
    private LocalDateTime createdDt;

    /**
     * 수정자
     */
    private String updatedId;

    /**
     * 수정일
     */
    private LocalDateTime updatedDt;

}
