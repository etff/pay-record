package com.toyproject.payrecord.domain.employee.domain;

import com.toyproject.payrecord.domain.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee save(Employee employee);

    Optional<Employee> findByEmail(String email);
}
