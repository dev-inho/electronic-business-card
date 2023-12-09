package com.inho.electronicbusinesscard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inho.electronicbusinesscard.repository.DepartmentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class DepartmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * 데이터베이스 초기화
     *  -. 레이어드 아키텍처 위배
     */
    @BeforeEach
    void setUp() {
        departmentMapper.findAll().forEach(departmentVO -> {
            departmentMapper.delete(objectMapper.convertValue(departmentVO, Map.class));
        });
    }

    @Test
    @DisplayName("소속 지회 생성")
    void add() throws Exception {
        Map<String, Object> inputs = new HashMap<>();

        inputs.put("code", "TEST_001");
        inputs.put("name", "test");
        inputs.put("userId", "test");

        mockMvc.perform(RestDocumentationRequestBuilders.post("/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputs)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("department-add-test",
                        requestFields(
                                fieldWithPath("code").description("소속 지회 코드"),
                                fieldWithPath("name").description("소속 지회명"),
                                fieldWithPath("userId").description("등록자")
                        ),
                        responseFields(
                                fieldWithPath("code").description("응답 코드"),
                                fieldWithPath("res").description("응답 상태"),
                                fieldWithPath("msg").description("응답 메세지"),
                                fieldWithPath("data").description("반환 데이터 없읍")
                        )))
                .andExpect(jsonPath("$.code").value(is(200)))
                .andExpect(jsonPath("$.res").value(is(true)))
                .andExpect(jsonPath("$.msg").value(is("소속 지회를 등록하였습니다.")));
    }

    @Test
    @DisplayName("소속 지회 수정")
    void modify() throws Exception {
        Map<String, Object> inputs = new HashMap<>();

        inputs.put("code", "TEST_001");
        inputs.put("name", "test");
        inputs.put("userId", "test");

        mockMvc.perform(RestDocumentationRequestBuilders.put("/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputs)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("department-modify-test",
                        requestFields(
                                fieldWithPath("code").description("소속 지회 코드"),
                                fieldWithPath("name").description("소속 지회명"),
                                fieldWithPath("userId").description("등록자")
                        ),
                        responseFields(
                                fieldWithPath("code").description("응답 코드"),
                                fieldWithPath("res").description("응답 상태"),
                                fieldWithPath("msg").description("응답 메세지"),
                                fieldWithPath("data").description("반환 데이터 없읍")
                        )))
                .andExpect(jsonPath("$.code").value(is(200)))
                .andExpect(jsonPath("$.res").value(is(true)))
                .andExpect(jsonPath("$.msg").value(is("소속 지회를 등록하였습니다.")));
    }

    @Test
    @DisplayName("소속 지회 단건 조회")
    void findById() throws Exception {
        Map<String, Object> inputs = new HashMap<>();

        inputs.put("code", "TEST_001");
        inputs.put("name", "테스트");
        inputs.put("createdId", "test");

        /* 소속 지회 저장 */
        departmentMapper.add(inputs);

        inputs = new HashMap<>();
        inputs.put("code", "TEST_001");

        /* 소속 지회 조회 */
        mockMvc.perform(RestDocumentationRequestBuilders.get("/department"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("department-find-by-id-test",
                        pathParameters(
                                parameterWithName("code").description("소속 지회 코드")
                        )
                ))
                .andExpect(jsonPath("$.code").value(is(200)))
                .andExpect(jsonPath("$.res").value(is(true)))
                .andExpect(jsonPath("$.msg").value(notNullValue()));
    }

    @Test
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

        /* 2. 소속 지회 전체 조회 */
        mockMvc.perform(RestDocumentationRequestBuilders.get("/department/list"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("department-find-by-id-test"))
                .andExpect(jsonPath("$.code").value(is(200)))
                .andExpect(jsonPath("$.res").value(is(true)))
                .andExpect(jsonPath("$.msg").value(notNullValue()));
    }

}