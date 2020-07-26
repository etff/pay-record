package com.toyproject.payrecord.employee.application;

import com.toyproject.payrecord.config.auth.jwt.JwtTokenProvider;
import com.toyproject.payrecord.company.application.CompanyNotExistedException;
import com.toyproject.payrecord.company.domain.Company;
import com.toyproject.payrecord.company.domain.CompanyRepository;
import com.toyproject.payrecord.employee.application.exception.EmailExistedException;
import com.toyproject.payrecord.employee.application.exception.EmailNotExistedException;
import com.toyproject.payrecord.employee.application.exception.PasswordWrongException;
import com.toyproject.payrecord.employee.domain.EmployeeRepository;
import com.toyproject.payrecord.employee.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmpService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;


    public Long singUp(String email, String password) {
        Optional<Employee> existed = employeeRepository.findByEmail(email);
        if (existed.isPresent()) {
            throw new EmailExistedException(email);
        }
        String encodedPassword = passwordEncoder.encode(password);
        Employee employee = new Employee(email, encodedPassword);

        Employee saved = employeeRepository.save(employee);
        return saved.getId();
    }

    public String authenticate(String email, String password) {
        String token = "";
        Employee employee = employeeRepository.findByEmail(email).orElseThrow(
                () -> new EmailNotExistedException(email));

        if (!passwordEncoder.matches(password, employee.getPassword())) {
            throw new PasswordWrongException();
        }
        try {
            token = jwtTokenProvider.createToken(employee.getId(), email, new ArrayList<>());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        return token;
    }

    public void updateCompany(Long empId, Long companyId) {
        Employee findEmployee = employeeRepository.findById(empId)
                .orElseThrow(EmailNotExistedException::new);

        Company findCompany = companyRepository.findById(companyId)
                .orElseThrow(CompanyNotExistedException::new);

        findEmployee.updateCompany(findCompany);
    }


}
