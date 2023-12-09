package com.inho.electronicbusinesscard.domain.member.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberSearchDTO {

    /**
     * 사용자 이름
     */
    @Size(max = 10, message = "이름은 10글자를 넘길 수 없습니다.")
    private String userName;

    /**
     * 사명
     */
    @Size(max = 20, message = "사명은 20글자를 넘길 수 없습니다.")
    private String company;

    /**
     * 소속 지회명
     */
    @Size(max = 20, message = "소속 지회명은 20글자를 넘길 수 없습니다.")
    private String department;

}
