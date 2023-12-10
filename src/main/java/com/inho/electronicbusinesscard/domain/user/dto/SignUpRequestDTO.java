package com.inho.electronicbusinesscard.domain.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 회원 가입 요청 시 사용되는 DTO
 */

@Data
public class SignUpRequestDTO {

    /**
     * 사용자 아이디
     */
    @NotEmpty(message = "사용자 아이디는 필수입니다.")
    private String userId;

    /**
     * 비밀번호
     */
    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;

    /**
     * 휴대전화 번호
     * 010-XXXX-XXXX
     */
    @NotEmpty(message = "휴대전화 번호는 필수입니다.")
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3,4})-\\d{4}$", message = "휴대전화 번호의 형식이 올바르지 않습니다.")
    private String phoneNumber;

    /**
     * 이름
     */
    @NotEmpty(message = "이름은 필수입니다.")
    private String userName;
}
