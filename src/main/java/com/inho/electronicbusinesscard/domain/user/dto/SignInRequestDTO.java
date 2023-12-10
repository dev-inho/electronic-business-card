package com.inho.electronicbusinesscard.domain.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 로그인 요청 시 사용되는 DTO
 */
@Data
public class SignInRequestDTO {

    /**
     * 사용자 아이디
     */
    @NotEmpty(message = "사용자 아이디는 필수입니다.")
    @Size(max = 30, message = "사용자 아이디는 30자를 넘을 수 없습니다.")
    private String userId;


    /**
     * 비밀번호
     */
    @NotEmpty(message = "비밀번호는 필수입니다.")
    @Size(max = 50, message = "비밀번호는 50자를 넘을 수 없습니다.")
    private String password;

}
