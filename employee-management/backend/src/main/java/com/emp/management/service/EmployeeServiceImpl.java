package com.emp.management.service;

import com.emp.management.dto.DashboardDTO;
import com.emp.management.dto.EmployeeDTO;
import com.emp.management.entity.Employee;
import com.emp.management.exception.DuplicateEmailException;
import com.emp.management.exception.EmployeeNotFoundException;
import com.emp.management.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees");
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        return toDTO(findById(id));
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        log.info("Creating employee with email: {}", dto.getEmail());
        if (repository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException(dto.getEmail());
        }
        Employee saved = repository.save(toEntity(dto));
        log.info("Employee created with ID: {}", saved.getId());
        return toDTO(saved);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        log.info("Updating employee with ID: {}", id);
        Employee existing = findById(id);
        if (!existing.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException(dto.getEmail());
        }
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setDepartment(dto.getDepartment());
        existing.setDesignation(dto.getDesignation());
        existing.setSalary(dto.getSalary());
        return toDTO(repository.save(existing));
    }

    @Override
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);
        findById(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> searchEmployees(String query) {
        log.info("Searching employees with query: {}", query);
        return repository.searchByNameOrEmail(query).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DashboardDTO getDashboardStats() {
        long total = repository.count();
        BigDecimal totalSalary = repository.sumAllSalaries();
        BigDecimal avgSalary = repository.avgAllSalaries();

        Map<String, Long> byDept = new HashMap<>();
        repository.countByDepartment().forEach(row -> byDept.put((String) row[0], (Long) row[1]));

        return DashboardDTO.builder()
                .totalEmployees(total)
                .totalSalary(totalSalary != null ? totalSalary : BigDecimal.ZERO)
                .avgSalary(avgSalary != null ? avgSalary : BigDecimal.ZERO)
                .employeesByDepartment(byDept)
                .build();
    }

    private Employee findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    private EmployeeDTO toDTO(Employee e) {
        return EmployeeDTO.builder()
                .id(e.getId()).name(e.getName()).email(e.getEmail())
                .phoneNumber(e.getPhoneNumber()).department(e.getDepartment())
                .designation(e.getDesignation()).salary(e.getSalary())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    private Employee toEntity(EmployeeDTO dto) {
        return Employee.builder()
                .name(dto.getName()).email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber()).department(dto.getDepartment())
                .designation(dto.getDesignation()).salary(dto.getSalary())
                .build();
    }
}
