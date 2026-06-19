package com.emp.management.repository;

import com.emp.management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findByDepartment(String department);

    boolean existsByEmail(String email);

    @Query("SELECT SUM(e.salary) FROM Employee e")
    BigDecimal sumAllSalaries();

    @Query("SELECT AVG(e.salary) FROM Employee e")
    BigDecimal avgAllSalaries();

    @Query("SELECT e.department, COUNT(e) FROM Employee e GROUP BY e.department")
    List<Object[]> countByDepartment();

    @Query("SELECT e FROM Employee e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(e.email) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Employee> searchByNameOrEmail(@Param("query") String query);
}
