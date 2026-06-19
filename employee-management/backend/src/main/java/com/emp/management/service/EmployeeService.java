package com.emp.management.service;

import com.emp.management.dto.DashboardDTO;
import com.emp.management.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(Long id);
    EmployeeDTO createEmployee(EmployeeDTO dto);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO dto);
    void deleteEmployee(Long id);
    List<EmployeeDTO> searchEmployees(String query);
    DashboardDTO getDashboardStats();
}
