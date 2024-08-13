package com.company.exercise.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.exercise.entity.Department;
import com.company.exercise.projection.DepartmentProjection;
import com.company.exercise.projection.DepartmentSummary;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Define custom query methods if needed
    List<Department> findByName(String name);

    // Custom query using @Query annotation
    @Query("SELECT d FROM Department d WHERE d.name = :name")
    Department findByNameCustom(@Param("name") String name);

    // Custom query to find departments with names that start with a given prefix
    @Query("SELECT d FROM Department d WHERE d.name LIKE :prefix%")
    List<Department> findDepartmentsStartingWith(@Param("prefix") String prefix);

    // Custom query to find all departments with a specific number of employees
    @Query("SELECT d FROM Department d WHERE SIZE(d.employees) = :size")
    List<Department> findByNumberOfEmployees(@Param("size") int size);

    // Find departments by name with pagination and sorting
    Page<Department> findByNameContaining(String name, Pageable pageable);

    // Interface-based projection
    @Query("SELECT d.id AS id, d.name AS name, COUNT(e.id) AS employeeCount FROM Department d LEFT JOIN d.employees e GROUP BY d.id, d.name")
    List<DepartmentProjection> findAllProjectedBy();

    // Class-based projection
    @Query("SELECT new com.company.exercise.projection.DepartmentSummary(d.id, d.name, COUNT(e)) " +
           "FROM Department d FULL JOIN d.employees e GROUP BY d.id, d.name")
    List<DepartmentSummary> findDepartmentSummaries();
}
