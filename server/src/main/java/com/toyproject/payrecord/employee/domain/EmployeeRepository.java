package com.toyproject.payrecord.employee.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee save(Employee employee);

    Optional<Employee> findByEmail(String email);
}
