package com.inho.electronicbusinesscard.domain.user.dto;

import com.inho.electronicbusinesscard.domain.authority.dto.AuthorityDTO;
import com.inho.electronicbusinesscard.domain.authority.vo.AuthorityVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    /**
     * 사용자 IDX(PK)
     */
    private long userIdx;

    /**
     * 사용자 아이디
     */
    private String userId;

    /**
     * 사용자 비밀번호
     */
    private String password;

    /**
     * 사용자 이름
     */
    private String userName;

    /**
     * 사용자 전화번호
     */
    private String phoneNumber;

    /**
     * 권한
     */
    private Set<AuthorityDTO> authorities;
}
