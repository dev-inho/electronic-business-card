package com.inho.electronicbusinesscard.config.handler;

import com.inho.electronicbusinesscard.domain.common.CommonResponseDTO;
import com.inho.electronicbusinesscard.domain.department.exception.InvalidDepartmentDataException;
import com.inho.electronicbusinesscard.domain.department.exception.NotFoundDepartmentException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global Exception Handler
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler {

    private final HttpServletRequest request;

    private final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    /**
     * 사용자 과실로 인한 에러 핸들러
     * @param e RuntimeException을 상속 받은 커스텀 Exception
     * @return {res: false, msg: String, code: 4XX, data: null}
     */
    @ExceptionHandler({
        NotFoundDepartmentException.class,
        InvalidDepartmentDataException.class
    })
    public ResponseEntity<CommonResponseDTO> clientExceptionHandler(RuntimeException e) {

        CommonResponseDTO responseDTO = CommonResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .msg(e.getMessage())
                .res(false)
                .build();

        log.debug("Client Exception : {}", e.getMessage());

        return ResponseEntity
                .badRequest()
                .body(responseDTO);
    }

    /**
     * 서버에서 예기치 발생한 에러
     * @param e 서버에서 발생하는 모든 에러
     * @return {res: false, msg: String, code: 5XX}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponseDTO> serverExceptionHandler(Exception e) {

        CommonResponseDTO responseDTO = CommonResponseDTO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .msg(e.getMessage())
                .res(false)
                .build();

        log.error("Server Exception : Error request {} to {}", request.getMethod(), request.getRequestURL(), e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseDTO);
    }

    /**
     * Validation 위반 에러
     * -. Validation 조건 위반 시 필드 선언 순으로 내려줌.
     * @param e BindException을 상속 받은 Exception
     * @return {res: false, msg: String, code: 4XX}
     */
    @ExceptionHandler({
        BindException.class,
        MethodArgumentNotValidException.class,
    })
    public ResponseEntity<CommonResponseDTO> validationExceptionHandler(BindException e) {

        /* 1. 에러 추출 */
        List<Map<String, String>> errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> {
                    Map<String, String> errorInfo = new HashMap<>();

                    errorInfo.put("msg", objectError.getDefaultMessage());
                    errorInfo.put("field", objectError.getObjectName());

                    /* 필드 관련 에러인 경우 */
                    if (objectError instanceof FieldError fieldError) {
                        errorInfo.put("field", fieldError.getField());
                    } else { /* 필드 관련 에러가 아닌 경우 */
                        errorInfo.put("field", objectError.getObjectName());
                    }

                    return errorInfo;
                })
                .collect(Collectors.toList());

        log.debug("Wrong Parameters : {}", e.getMessage());

        /* 2. DTO 필드 선언 순으로 정렬 */
        Class<?> dtoClass = e.getTarget().getClass();
        Field[] fields = dtoClass.getDeclaredFields();
        List<String> sortedErrorList = Arrays.stream(fields)
                .map(Field::getName)
                .flatMap(fieldName -> errors.stream()
                        .filter(error -> error.get("field").equals(fieldName))
                        .map(error -> error.get("msg")))
                .toList();

        /* 3. Client에게 응답 리턴 */
        CommonResponseDTO responseDTO = CommonResponseDTO.builder()
                .msg(!sortedErrorList.isEmpty() ? sortedErrorList.get(0) : "")
                .code(HttpStatus.BAD_REQUEST.value())
                .res(false)
                .build();

        return ResponseEntity
                .badRequest()
                .body(responseDTO);
    }
}
