package com.inho.electronicbusinesscard.service;

import com.inho.electronicbusinesscard.domain.department.dto.DepartmentDTO;
import com.inho.electronicbusinesscard.repository.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentMapper departmentMapper;

    @Override
    public void add() {

    }

    @Override
    public void modify() {

    }

    @Override
    public List<DepartmentDTO> findAll() {
        return null;
    }

    @Override
    public DepartmentDTO findById() {
        return null;
    }

}
