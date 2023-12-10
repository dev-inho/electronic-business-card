package com.inho.electronicbusinesscard.domain.user.vo;

import com.inho.electronicbusinesscard.domain.authority.vo.AuthorityVO;
import com.inho.electronicbusinesscard.domain.common.BaseVO;
import com.inho.electronicbusinesscard.domain.user.dto.UserDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@ToString(callSuper = true)
public class UserVO extends BaseVO {

    /**
     * 사용자 IDX(PK)
     */
    private long userIdx;

    /**
     * 사용자 아이디
     */
    private final String userId;

    /**
     * 사용자 비밀번호
     */
    private final String password;

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
    private Set<AuthorityVO> authorities;

    public void setAuthorities(Set<AuthorityVO> authorities) {
        this.authorities = authorities;
    }

    /**
     * 활동 여부
     */
    private boolean isActivated;

    public UserVO(String createdId, LocalDateTime createdDt, String updatedId, LocalDateTime updatedDt,
                  Long userIdx, String userId, String password, String userName, String phoneNumber, Boolean isActivated) {
        super(createdId, createdDt, updatedId, updatedDt);

        this.userIdx = userIdx;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.isActivated = isActivated;
    }

    public UserVO(String userId, String passowrd, Set<AuthorityVO> authorities) {
        super();
        this.userId = userId;
        this.password = passowrd;
        this.authorities = authorities;
    }

    /**
     * UserVO -> UserDTO
     * -. 비밀번호 제외
     * @return UserDTO
     */
    public UserDTO toDTO() {
        return UserDTO.builder()
                .userIdx(userIdx)
                .userId(userId)
                .userName(userName)
                .phoneNumber(phoneNumber)
                .authorities(authorities.stream().map(AuthorityVO::toDTO).collect(Collectors.toSet()))
                .build();
    }
}
