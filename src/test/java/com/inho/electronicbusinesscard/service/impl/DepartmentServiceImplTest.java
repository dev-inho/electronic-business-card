package com.inho.electronicbusinesscard.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inho.electronicbusinesscard.domain.department.dto.DepartmentDTO;
import com.inho.electronicbusinesscard.domain.department.dto.DepartmentSaveRequestDTO;
import com.inho.electronicbusinesscard.domain.department.dto.DepartmentSearchRequestDTO;
import com.inho.electronicbusinesscard.repository.DepartmentMapper;
import com.inho.electronicbusinesscard.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
class DepartmentServiceImplTest {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * 데이터베이스 초기화
     *  -. 레이어드 아키텍처 위배
     */
    @BeforeEach
    void setUp() {
        departmentService.findAll().forEach(departmentDTO -> {
            departmentMapper.delete(objectMapper.convertValue(departmentDTO, Map.class));
        });
    }

    @Test
    @Transactional
    @DisplayName("소속 지회 추가")
    void add() throws Exception {
        DepartmentSaveRequestDTO saveDTO = new DepartmentSaveRequestDTO();

        /* 1 데이터 저장 */
        saveDTO.setCode("TEST_0001");
        saveDTO.setName("테스트");
        saveDTO.setUserId("test");

        departmentService.add(saveDTO);

        /* 2. 데이터 확인 */
        DepartmentSearchRequestDTO searchRequestDTO = new DepartmentSearchRequestDTO();

        searchRequestDTO.setCode("TEST_0001");

        DepartmentDTO byId = departmentService.findById(searchRequestDTO);

        /* 3. 단정문 */
        assertNotNull(byId);
        assertEquals(byId.getCode(), "TEST_0001");
        assertEquals(byId.getName(), "테스트");
        assertEquals(byId.getCreatedId(), "test");

    }

    @Test
    @Transactional
    @DisplayName("소속 지회 수정")
    void modify() throws Exception {
        DepartmentSaveRequestDTO saveDTO = new DepartmentSaveRequestDTO();

        /* 1 데이터 저장 */
        saveDTO.setCode("TEST_0001");
        saveDTO.setName("테스트");
        saveDTO.setUserId("test");

        departmentService.add(saveDTO);

        /* 2. 데이터 조회 */
        DepartmentSearchRequestDTO searchRequestDTO = new DepartmentSearchRequestDTO();

        searchRequestDTO.setCode("TEST_0001");

        DepartmentDTO byId = departmentService.findById(searchRequestDTO);

        /* 3. 데이터 수정 */
        String changedName = byId.getName() + " CHANGED";
        saveDTO = new DepartmentSaveRequestDTO();

        saveDTO.setCode(byId.getCode());
        saveDTO.setName(changedName);
        saveDTO.setUserId("test");

        departmentService.modify(saveDTO);

        /* 4. 수정한 데이터 재조회 */
        byId = departmentService.findById(searchRequestDTO);

        /* 5. 단정문 */
        assertNotNull(byId);
        assertEquals(byId.getName(), changedName);
        assertNotNull(byId.getUpdatedDt());
        assertNotNull(byId.getUpdatedId());
        assertEquals(byId.getUpdatedId(), "test");

    }

    @Test
    @Transactional
    @DisplayName("소속 지회 전체 조회")
    void findAll() throws Exception {
        Map<String, Object> firstInputs = new HashMap<>();
        Map<String, Object> secondInputs = new HashMap<>();
        Map<String, Object> thirdInputs = new HashMap<>();

        firstInputs.put("code", "TEST_001");
        firstInputs.put("name", "테스트");
        firstInputs.put("createdId", "test");

        secondInputs.put("code", "TEST_002");
        secondInputs.put("name", "테스트");
        secondInputs.put("createdId", "test");

        thirdInputs.put("code", "TEST_003");
        thirdInputs.put("name", "테스트");
        thirdInputs.put("createdId", "test");

        /* 1. 데이터 저장 */
        departmentMapper.add(firstInputs);
        departmentMapper.add(secondInputs);
        departmentMapper.add(thirdInputs);

        /* 2. 데이터 조회 */
        List<DepartmentDTO> departments = departmentService.findAll();

        /* 3. 단정문 */
        assertNotNull(departments);
        assertEquals(departments.size(), 3);
    }

    @Test
    @Transactional
    @DisplayName("소속 지회 단건 조회")
    void findById() throws Exception {
        DepartmentSaveRequestDTO saveDTO = new DepartmentSaveRequestDTO();

        /* 1 데이터 저장 */
        saveDTO.setCode("TEST_0001");
        saveDTO.setName("테스트");
        saveDTO.setUserId("test");

        departmentService.add(saveDTO);

        /* 2. 데이터 확인 */
        DepartmentSearchRequestDTO searchRequestDTO = new DepartmentSearchRequestDTO();

        searchRequestDTO.setCode("TEST_0001");

        DepartmentDTO byId = departmentService.findById(searchRequestDTO);

        /* 3. 단정문 */
        assertNotNull(byId);
        assertEquals(byId.getCode(), "TEST_0001");
        assertEquals(byId.getName(), "테스트");
        assertEquals(byId.getCreatedId(), "test");
    }

}