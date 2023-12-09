package com.inho.electronicbusinesscard.domain.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponseDTO {

    /**
     * 응답 메세지
     */
    private String msg;

    /**
     * 상태 코드
     */
    private int code;

    /**
     * 반환 데이터
     */
    private List<Map<String, Object>> data;

    /**
     * 응답 상태
     */
    private boolean res;

}
