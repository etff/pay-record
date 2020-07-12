package com.toyproject.payrecord.domain.employee.application;

import com.toyproject.payrecord.domain.employee.domain.EmpRepository;
import com.toyproject.payrecord.domain.employee.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmpService {

    private final EmpRepository empRepository;
    private final PasswordEncoder passwordEncoder;

    public Employee singUp(String email, String password) {
        Optional<Employee> existed = empRepository.findByEmail(email);
        if (existed.isPresent()) {
            throw new EmailExistedException(email);
        }
        String encodedPassword = passwordEncoder.encode(password);
        Employee employee = new Employee(email, encodedPassword);

        return empRepository.save(employee);
    }

    public EmpResponseDto authenticate(String email, String password) {
        Employee employee = empRepository.findByEmail(email).orElseThrow(
                () ->new EmailNotExistedException(email));

        if (!passwordEncoder.matches(password, employee.getPassword())) {
            throw new PasswordWrongException();
        }

        return EmpResponseDto.of(employee);
    }
}
