package com.inho.electronicbusinesscard.domain.user.vo;

import com.inho.electronicbusinesscard.domain.common.Authority;
import com.inho.electronicbusinesscard.domain.common.BaseVO;
import com.inho.electronicbusinesscard.domain.department.vo.DepartmentVO;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 사용자 정보 VO
 */
@Getter
public class UserVO extends BaseVO {

    /**
     * 사용자 번호
     */
    private final long userIdx;

    /**
     * 이름
     */
    private final String userName;

    /**
     * 권한
     */
    private final Authority authority;

    /**
     * 소속 회사
     */
    private final String company;

    /**
     * 명장 인증 년도
     */
    private final LocalDate certificationDt;

    /**
     * 소속
     */
    private final DepartmentVO department;


    public UserVO(String createdId, LocalDateTime createdDt, String updatedId, LocalDateTime updatedDt, long userIdx,
                  String userName, Authority authority, String company, LocalDate certificationDt, DepartmentVO department) {
        super(createdId, createdDt, updatedId, updatedDt);
        this.userIdx = userIdx;
        this.userName = userName;
        this.authority = authority;
        this.company = company;
        this.certificationDt = certificationDt;
        this.department = department;
    }

}
