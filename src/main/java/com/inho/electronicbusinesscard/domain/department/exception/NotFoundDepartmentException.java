package com.inho.electronicbusinesscard.domain.department.exception;

/**
 * 소속 지회를 찾을 수 없는 경우 발생
 */
public class NotFoundDepartmentException extends RuntimeException {

    private static final String errorMessage = "소속 지회를 찾을 수 없습니다.";

    public NotFoundDepartmentException() {
        super(errorMessage);
    }

    public NotFoundDepartmentException(String errorMessage) {
        super(errorMessage);
    }

    public NotFoundDepartmentException(Throwable cause) {
        super(cause);
    }

    public NotFoundDepartmentException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

}
