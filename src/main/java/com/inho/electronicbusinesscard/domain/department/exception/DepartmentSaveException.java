package com.inho.electronicbusinesscard.domain.department.exception;

/**
 * 소속 지회를 저장하지 못한 경우
 */
public class DepartmentSaveException extends RuntimeException {

    private static final String errorMessage = "소속 지회를 저장하지 못했습니다.";

    public DepartmentSaveException() {
        super(errorMessage);
    }

    public DepartmentSaveException(String errorMessage) {
        super(errorMessage);
    }

    public DepartmentSaveException(Throwable cause) {
        super(cause);
    }

    public DepartmentSaveException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

}
