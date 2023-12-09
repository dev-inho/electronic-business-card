package com.inho.electronicbusinesscard.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inho.electronicbusinesscard.domain.department.dto.DepartmentDTO;
import com.inho.electronicbusinesscard.domain.department.exception.NotFoundDepartmentException;
import com.inho.electronicbusinesscard.domain.department.vo.DepartmentVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@ExtendWith(SpringExtension.class)
class DepartmentMapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 데이터베이스 초기화
     */
    @BeforeEach
    void setUp() {
        departmentMapper.findAll().forEach(departmentDTO -> {
            departmentMapper.delete(objectMapper.convertValue(departmentDTO, Map.class));
        });
    }

    @Test
    @Transactional
    @DisplayName("사용자 등록")
    void add() throws Exception {
        Map<String, Object> inputs = new HashMap<>();

        /* 1. 데이터 저장 */
        inputs.put("code", "TEST_001");
        inputs.put("name", "테스트");
        inputs.put("createdId", "test");

        int effectedQueryRow = departmentMapper.add(inputs);

        assertEquals(effectedQueryRow, 1);

        /* 2. 데이터 조회  */
        inputs = new HashMap<>();
        inputs.put("code", "TEST_001");

        DepartmentVO departmentVO = departmentMapper.findById(inputs)
                .orElseThrow(NotFoundDepartmentException::new);

        /* 3. 단정문 */
        assertEquals("TEST_001", departmentVO.getCode());
        assertEquals("테스트", departmentVO.getName());
    }

    @Test
    @Transactional
    @DisplayName("사용자 수정")
    void modify() throws Exception {
        Map<String, Object> inputs = new HashMap<>();

        /* 1. 데이터 저장 */
        inputs.put("code", "TEST_001");
        inputs.put("name", "테스트");
        inputs.put("createdId", "test");

        departmentMapper.add(inputs);

        /* 2. 데이터 수정 */
        inputs = new HashMap<>();

        inputs.put("code", "TEST_001");
        inputs.put("name", "이름 바뀐 테스트");
        inputs.put("updatedId", "test");

        departmentMapper.modify(inputs);

        /* 3. 데이터 확인 */
        inputs = new HashMap<>();

        inputs.put("code", "TEST_001");

        DepartmentVO departmentVO = departmentMapper.findById(inputs)
                .orElseThrow(NotFoundDepartmentException::new);

        System.out.println(departmentVO);

        /* 4. 단정문 */
        assertNotNull(departmentVO);
        assertEquals(departmentVO.getCode(), "TEST_001");
        assertEquals(departmentVO.getName(), "이름 바뀐 테스트");
        assertNotNull(departmentVO.getUpdatedDt());
        assertNotNull(departmentVO.getUpdatedId());
    }

    @Test
    @Transactional
    @DisplayName("사용자 정보 전체 조회")
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
        List<DepartmentVO> departments = departmentMapper.findAll();

        /* 3. 단정문 */
        assertNotNull(departments);
        assertEquals(departments.size(), 3);
    }

    @Test
    @DisplayName("사용자 정보 단건 조회")
    void findById() throws Exception {
        Map<String, Object> inputs = new HashMap<>();

        /* 1. 데이터 저장 */
        inputs.put("code", "TEST_001");
        inputs.put("name", "테스트");
        inputs.put("createdId", "test");

        departmentMapper.add(inputs);

        inputs = new HashMap<>();

        inputs.put("code", "TEST_001");

        /* 2. 데이터 수정 */
        DepartmentVO departmentVO = departmentMapper.findById(inputs)
                .orElseThrow(NotFoundDepartmentException::new);

        /* 3. 단정문 */
        assertEquals("TEST_001", departmentVO.getCode());
        assertEquals("테스트", departmentVO.getName());
    }

}