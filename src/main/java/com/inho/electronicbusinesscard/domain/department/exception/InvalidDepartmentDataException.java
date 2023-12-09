package com.inho.electronicbusinesscard.domain.department.exception;

/**
 * 소속 지회에 유효하지 않은 정보가 들어왔을 경우
 */
public class InvalidDepartmentDataException extends  RuntimeException {

    private static final String errorMessage = "소속지회 저장 및 수정 시 유효하지 못한 정보가 기입되었습니다.";

    public InvalidDepartmentDataException() {
        super(errorMessage);
    }

    public InvalidDepartmentDataException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidDepartmentDataException(Throwable cause) {
        super(cause);
    }

    public InvalidDepartmentDataException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

}
