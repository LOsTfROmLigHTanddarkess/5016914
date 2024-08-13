package com.company.exercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.exercise.entity.Department;
import com.company.exercise.repository.DepartmentRepository;
import com.company.exercise.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public Page<Department> getDepartments(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        // Handle sorting
        Sort sortOrder = Sort.by(Sort.Order.by(sort[0]));
        if (sort.length > 1 && sort[1].equalsIgnoreCase("desc")) {
            sortOrder = sortOrder.descending();
        }

        Pageable pageable = PageRequest.of(page, size, sortOrder);

        // Return departments filtered by name with pagination and sorting
        return departmentService.getDepartmentsByName(name != null ? name : "", pageable);
    }

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
