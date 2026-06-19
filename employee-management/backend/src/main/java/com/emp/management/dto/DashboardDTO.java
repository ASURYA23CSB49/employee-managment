package com.emp.management.dto;

import java.math.BigDecimal;
import java.util.Map;

public class DashboardDTO {
    private long totalEmployees;
    private BigDecimal totalSalary;
    private BigDecimal avgSalary;
    private Map<String, Long> employeesByDepartment;

    public DashboardDTO() {}

    // Getters
    public long getTotalEmployees() { return totalEmployees; }
    public BigDecimal getTotalSalary() { return totalSalary; }
    public BigDecimal getAvgSalary() { return avgSalary; }
    public Map<String, Long> getEmployeesByDepartment() { return employeesByDepartment; }

    // Setters
    public void setTotalEmployees(long totalEmployees) { this.totalEmployees = totalEmployees; }
    public void setTotalSalary(BigDecimal totalSalary) { this.totalSalary = totalSalary; }
    public void setAvgSalary(BigDecimal avgSalary) { this.avgSalary = avgSalary; }
    public void setEmployeesByDepartment(Map<String, Long> employeesByDepartment) { this.employeesByDepartment = employeesByDepartment; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final DashboardDTO d = new DashboardDTO();
        public Builder totalEmployees(long v) { d.totalEmployees = v; return this; }
        public Builder totalSalary(BigDecimal v) { d.totalSalary = v; return this; }
        public Builder avgSalary(BigDecimal v) { d.avgSalary = v; return this; }
        public Builder employeesByDepartment(Map<String, Long> v) { d.employeesByDepartment = v; return this; }
        public DashboardDTO build() { return d; }
    }
}
