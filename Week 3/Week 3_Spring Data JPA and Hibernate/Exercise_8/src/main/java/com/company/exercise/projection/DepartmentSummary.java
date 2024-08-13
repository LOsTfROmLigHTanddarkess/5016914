package com.company.exercise.projection;

public class DepartmentSummary {
    private Long id;
    private String name;
    private long employeeCount;

    public DepartmentSummary(Long id, String name, long employeeCount) {
        this.id = id;
        this.name = name;
        this.employeeCount = employeeCount;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(long employeeCount) {
        this.employeeCount = employeeCount;
    }
}
