package com.inho.electronicbusinesscard.domain.user.exception;

/**
 * 이미 중복되어 있는 사용자 등록 시
 */
public class DuplicateUserException extends RuntimeException {

    private static final String errorMessage = "이미 등록되어 있는 사용자입니다.";

    public DuplicateUserException() {
        super(errorMessage);
    }

    public DuplicateUserException(String errorMessage) {
        super(errorMessage);
    }

    public DuplicateUserException(Throwable cause) {
        super(cause);
    }

    public DuplicateUserException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

}
