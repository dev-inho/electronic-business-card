package com.inho.electronicbusinesscard.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inho.electronicbusinesscard.domain.department.dto.DepartmentDTO;
import com.inho.electronicbusinesscard.domain.department.dto.DepartmentSaveRequestDTO;
import com.inho.electronicbusinesscard.domain.department.dto.DepartmentSearchRequestDTO;
import com.inho.electronicbusinesscard.domain.department.exception.DepartmentSaveException;
import com.inho.electronicbusinesscard.domain.department.exception.InvalidDepartmentDataException;
import com.inho.electronicbusinesscard.domain.department.exception.NotFoundDepartmentException;
import com.inho.electronicbusinesscard.domain.department.vo.DepartmentVO;
import com.inho.electronicbusinesscard.repository.DepartmentMapper;
import com.inho.electronicbusinesscard.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentMapper departmentMapper;

    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void add(DepartmentSaveRequestDTO departmentSaveRequestDTO) {

        try {
            Map<String, Object> departmentMap = objectMapper.convertValue(departmentSaveRequestDTO.toDTO(), Map.class);
            int effectedQueryRow = departmentMapper.add(departmentMap);

            if (effectedQueryRow != 1) {
                log.error("소속 지회를 저장하지 못했습니다. : {} / {}", departmentSaveRequestDTO, effectedQueryRow);
                throw new DepartmentSaveException();
            }

        } catch (InvalidDepartmentDataException e) {
            throw new InvalidDepartmentDataException(e);
        } catch (RuntimeException e) {
            throw new DepartmentSaveException(e);
        }

    }

    @Override
    @Transactional
    public void modify(DepartmentSaveRequestDTO departmentSaveRequestDTO) {

        try {
            Map<String, Object> departmentMap = objectMapper.convertValue(departmentSaveRequestDTO.toDTO(), Map.class);
            int effectedQueryRow = departmentMapper.modify(departmentMap);

            if (effectedQueryRow != 1) {
                log.error("소속 지회를 수정하지 못했습니다. : {} / {}", departmentSaveRequestDTO, effectedQueryRow);
                throw new DepartmentSaveException();
            }

        } catch (InvalidDepartmentDataException e) {
            throw new InvalidDepartmentDataException(e);
        } catch (RuntimeException e) {
            throw new DepartmentSaveException(e);
        }

    }

    @Override
    public List<DepartmentDTO> findAll() {
        return departmentMapper.findAll()
                .stream()
                .map(DepartmentVO::toDTO)
                .toList();
    }

    @Override
    public DepartmentDTO findById(DepartmentSearchRequestDTO departmentSearchRequestDTO) {
        DepartmentVO departmentVO = null;

        try {
            Map<String, Object> departmentMap = objectMapper.convertValue(departmentSearchRequestDTO.toDTO(), Map.class);

            departmentVO = departmentMapper.findById(departmentMap)
                    .orElseThrow(NotFoundDepartmentException::new);

        } catch (InvalidDepartmentDataException e) {
            throw new InvalidDepartmentDataException(e);
        }

        return departmentVO.toDTO();
    }

}
