package com.inho.electronicbusinesscard.domain.authority.vo;

import com.inho.electronicbusinesscard.domain.authority.dto.AuthorityDTO;
import com.inho.electronicbusinesscard.domain.common.BaseVO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class AuthorityVO extends BaseVO {

    private long userIdx;

    private String authorityName;

    public AuthorityVO(String createdId, LocalDateTime createdDt, String updatedId, LocalDateTime updatedDt,
                       long userIdx, String authorityName) {

        this.userIdx = userIdx;
        this.authorityName = authorityName;
    }

    public AuthorityVO(String authorityName) {
        this.authorityName = authorityName;
    }

    /**
     * AuthorityVO -> AuthorityDTO
     * @return
     */
    public AuthorityDTO toDTO() {
        return AuthorityDTO.builder()
                .userIdx(userIdx)
                .authorityName(authorityName)
                .build();
    }
}
