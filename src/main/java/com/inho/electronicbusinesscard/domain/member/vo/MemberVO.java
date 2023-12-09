package com.inho.electronicbusinesscard.domain.member.vo;

import com.inho.electronicbusinesscard.domain.common.BaseVO;
import com.inho.electronicbusinesscard.domain.department.vo.DepartmentVO;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 사용자 정보 VO
 */
@Getter
public class MemberVO extends BaseVO {

    /**
     * 사용자 번호
     */
    private final long memberIdx;

    /**
     * 이름
     */
    private final String memberName;

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


    public MemberVO(String createdId, LocalDateTime createdDt, String updatedId, LocalDateTime updatedDt, long memberIdx,
                    String memberName, String company, LocalDate certificationDt, DepartmentVO department) {
        super(createdId, createdDt, updatedId, updatedDt);
        this.memberIdx = memberIdx;
        this.memberName = memberName;
        this.company = company;
        this.certificationDt = certificationDt;
        this.department = department;
    }

}
