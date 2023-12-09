package com.inho.electronicbusinesscard.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class BaseVO {

    /**
     * 생성자
     */
    private final String createdId;

    /**
     * 생성일
     */
    private final LocalDateTime createdDt;

    /**
     * 수정자
     */
    private final String updatedId;

    /**
     * 수정일
     */
    private final LocalDateTime updatedDt;

}
