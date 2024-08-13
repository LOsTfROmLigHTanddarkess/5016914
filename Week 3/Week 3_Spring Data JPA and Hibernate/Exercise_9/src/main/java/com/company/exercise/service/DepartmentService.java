package com.company.exercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.exercise.entity.Department;
import com.company.exercise.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Page<Department> getDepartmentsByName(String name, Pageable pageable) {
        return departmentRepository.findByNameContaining(name, pageable);
    }
}
