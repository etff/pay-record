package com.toyproject.payrecord.domain.employee.application;

import com.toyproject.payrecord.domain.employee.domain.EmpRepository;
import com.toyproject.payrecord.domain.employee.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpService {

    private final EmpRepository empRepository;

    public Employee singUp(String email, String password) {
        Employee employee = new Employee(email, password);

        return empRepository.save(employee);
    }
}
