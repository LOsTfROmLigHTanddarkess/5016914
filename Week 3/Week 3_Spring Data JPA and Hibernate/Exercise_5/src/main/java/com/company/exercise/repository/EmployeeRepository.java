package com.company.exercise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.exercise.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Define custom query methods if needed
    Employee findByEmail(String email);
    
    List<Employee> findByDepartmentName(String departmentName);

    // Custom query using @Query annotation
    @Query("SELECT e FROM Employee e WHERE e.name = :name")
    List<Employee> findByName(@Param("name") String name);

    // Custom query using @Query annotation with like operator
    @Query("SELECT e FROM Employee e WHERE e.department.name LIKE :prefix%")
    List<Employee> findEmployeesInDepartmentsStartingWith(@Param("prefix") String prefix);
}
