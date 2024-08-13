package com.company.exercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.exercise.entity.Employee;
import com.company.exercise.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> getEmployeesByName(String name, Pageable pageable) {
        return employeeRepository.findByNameContaining(name, pageable);
    }

    public Page<Employee> getEmployeesByDepartment(Long departmentId, Pageable pageable) {
        return employeeRepository.findByDepartmentId(departmentId, pageable);
    }
}
