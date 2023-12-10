package com.inho.electronicbusinesscard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inho.electronicbusinesscard.domain.common.CommonResponseDTO;
import com.inho.electronicbusinesscard.domain.department.dto.DepartmentDTO;
import com.inho.electronicbusinesscard.domain.department.dto.DepartmentSaveRequestDTO;
import com.inho.electronicbusinesscard.domain.department.dto.DepartmentSearchRequestDTO;
import com.inho.electronicbusinesscard.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    private final ObjectMapper objectMapper;

    private final DepartmentService departmentService;

    /**
     * 소속 지회 생성
     * @param departmentSaveRequestDTO {code: String, name: String, userId: String}
     * @return ResponseEntity<CommonResponseDTO> {res: boolean, msg: String, code: int, data: List}
     */
    @PostMapping
    public ResponseEntity<CommonResponseDTO> add(@Validated DepartmentSaveRequestDTO departmentSaveRequestDTO) {

        departmentService.add(departmentSaveRequestDTO);

        return ResponseEntity
                .ok()
                .body(CommonResponseDTO.builder()
                        .code(HttpStatus.OK.value())
                        .msg("소속 지회를 등록하였습니다.")
                        .res(true)
                        .build());
    }

    /**
     * 소속 지회 수정
     * @param departmentSaveRequestDTO {code: String, name: String, userId: String}
     * @return ResponseEntity<CommonResponseDTO> {res: boolean, msg: String, code: int, data: List}
     */
    @PutMapping
    public ResponseEntity<CommonResponseDTO> modify(@Validated DepartmentSaveRequestDTO departmentSaveRequestDTO) {

        departmentService.modify(departmentSaveRequestDTO);

        return ResponseEntity
                .ok()
                .body(CommonResponseDTO.builder()
                        .code(HttpStatus.OK.value())
                        .msg("소속 지회를 수정하였습니다.")
                        .res(true)
                        .build());

    }

    /**
     * 소속 지회 단건 조회
     * @param departmentSearchRequestDTO {code: String}
     * @return ResponseEntity<CommonResponseDTO> {res: boolean, msg: String, code: int, data: List}
     */
    @GetMapping
    public ResponseEntity<CommonResponseDTO> findById(@Validated DepartmentSearchRequestDTO departmentSearchRequestDTO) {
        List<Map<String, Object>> departments = new ArrayList<>();

        DepartmentDTO byId = departmentService.findById(departmentSearchRequestDTO);

        departments.add(objectMapper.convertValue(byId, Map.class));

        return ResponseEntity
                .ok()
                .body(CommonResponseDTO.builder()
                        .code(HttpStatus.OK.value())
                        .msg(byId.getName() + "의 정보입니다.")
                        .res(true)
                        .data(departments)
                        .build());
    }

    /**
     * 소속 지회 정보 반환
     * @return ResponseEntity<CommonResponseDTO> {res: boolean, msg: String, code: int, data: List}
     */
    @GetMapping("/list")
    public ResponseEntity<CommonResponseDTO> findAll() {

        List<Map<String, Object>> departments = new ArrayList<>();

        departmentService.findAll().forEach(departmentDTO -> {
            departments.add(objectMapper.convertValue(departmentDTO, Map.class));
        });

        return ResponseEntity
                .ok()
                .body(CommonResponseDTO.builder()
                        .code(HttpStatus.OK.value())
                        .msg(departments.size() + " 의 개의 정보입니다.")
                        .res(true)
                        .data(departments)
                        .build());
    }

}
