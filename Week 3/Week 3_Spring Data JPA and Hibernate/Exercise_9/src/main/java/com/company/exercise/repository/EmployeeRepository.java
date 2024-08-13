package com.company.exercise.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.exercise.entity.Employee;
import com.company.exercise.projection.EmployeeProjection;
import com.company.exercise.projection.EmployeeSummary;

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

    // Find employees by name with pagination and sorting
    Page<Employee> findByNameContaining(String name, Pageable pageable);

    // Find all employees by department ID with pagination and sorting
    Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);

    // Interface-based projection
    @Query("SELECT e.id AS id, e.name AS name, e.email AS email, d.name AS departmentName FROM Employee e JOIN e.department d WHERE d.id = :departmentId")
    List<EmployeeProjection> findEmployeesByDepartmentId(@Param("departmentId") Long departmentId);

    // Class-based projection
    @Query("SELECT new com.company.exercise.projection.EmployeeSummary(e.id, e.name, e.email, d.name) " +
           "FROM Employee e JOIN e.department d")
    List<EmployeeSummary> findEmployeeSummaries();
}
