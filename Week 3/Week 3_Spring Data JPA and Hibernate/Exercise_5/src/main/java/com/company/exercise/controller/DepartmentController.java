package com.company.exercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.exercise.entity.Department;
import com.company.exercise.repository.DepartmentRepository;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/name/{name}")
    public Department getDepartmentByName(@PathVariable String name) {
        return departmentRepository.findByNameCustom(name);
    }

    @GetMapping("/namePrefix/{prefix}")
    public List<Department> getDepartmentsByPrefix(@PathVariable String prefix) {
        return departmentRepository.findDepartmentsStartingWith(prefix);
    }

    @GetMapping("/employeeCount/{size}")
    public List<Department> getDepartmentsByEmployeeCount(@PathVariable int size) {
        return departmentRepository.findByNumberOfEmployees(size);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        Department department = departmentRepository.findById(id).orElse(null);

        if (department != null) {
            department.setName(departmentDetails.getName());
            return departmentRepository.save(department);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentRepository.deleteById(id);
    }
}
