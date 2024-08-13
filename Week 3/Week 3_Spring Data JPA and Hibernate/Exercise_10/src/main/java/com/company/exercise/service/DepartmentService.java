package com.company.exercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.exercise.entity.Department;
import com.company.exercise.repository.DepartmentRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void batchInsertDepartments(List<Department> departments) {
        int batchSize = 50;  // Configure your batch size here
        for (int i = 0; i < departments.size(); i++) {
            entityManager.persist(departments.get(i));
            if (i % batchSize == 0 && i > 0) {
                // Flush a batch of inserts and release memory.
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }

    public Page<Department> getDepartmentsByName(String name, Pageable pageable) {
        return departmentRepository.findByNameContaining(name, pageable);
    }
}
