package com.company.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.exercise.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Define custom query methods if needed
    Department findByName(String name);
}
