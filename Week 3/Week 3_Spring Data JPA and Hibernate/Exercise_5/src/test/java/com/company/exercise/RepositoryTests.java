package com.company.exercise;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.company.exercise.entity.Department;
import com.company.exercise.entity.Employee;
import com.company.exercise.repository.DepartmentRepository;
import com.company.exercise.repository.EmployeeRepository;

@SpringBootTest
public class RepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testEmployeeRepository() {
        Department department = new Department();
        department.setName("HR");
        departmentRepository.save(department);

        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setDepartment(department);
        employeeRepository.save(employee);

        Employee foundEmployee = employeeRepository.findByEmail("john.doe@example.com");
        assertNotNull(foundEmployee);
        assertEquals("John Doe", foundEmployee.getName());
    }

    @Test
    public void testDepartmentRepository() {
        Department department = new Department();
        department.setName("IT");
        departmentRepository.save(department);

        List<Department> foundDepartment = departmentRepository.findByName("IT");
        assertNotNull(foundDepartment);
        assertEquals("IT", ((Department) foundDepartment).getName());
    }
}
