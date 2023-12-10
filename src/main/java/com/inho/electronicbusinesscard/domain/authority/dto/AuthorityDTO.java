package com.inho.electronicbusinesscard.domain.authority.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDTO {
    private long userIdx;

    private String authorityName;

}
