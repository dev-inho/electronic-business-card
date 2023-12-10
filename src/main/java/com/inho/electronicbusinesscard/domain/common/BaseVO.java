package com.inho.electronicbusinesscard.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseVO {

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
