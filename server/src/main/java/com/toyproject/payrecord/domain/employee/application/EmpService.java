package com.toyproject.payrecord.domain.employee.application;

import com.toyproject.payrecord.domain.company.application.CompanyNotExistedException;
import com.toyproject.payrecord.domain.company.domain.Company;
import com.toyproject.payrecord.domain.company.domain.CompanyRepository;
import com.toyproject.payrecord.domain.employee.application.dto.EmpResponseDto;
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
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public Long singUp(String email, String password) {
        Optional<Employee> existed = empRepository.findByEmail(email);
        if (existed.isPresent()) {
            throw new EmailExistedException(email);
        }
        String encodedPassword = passwordEncoder.encode(password);
        Employee employee = new Employee(email, encodedPassword);

        Employee saved = empRepository.save(employee);
        return saved.getId();
    }

    public EmpResponseDto authenticate(String email, String password) {
        Employee employee = empRepository.findByEmail(email).orElseThrow(
                () ->new EmailNotExistedException(email));

        if (!passwordEncoder.matches(password, employee.getPassword())) {
            throw new PasswordWrongException();
        }

        return EmpResponseDto.of(employee);
    }

    public void updateCompany(Long empId, Long companyId) {
        Employee findEmployee = empRepository.findById(empId)
                .orElseThrow(EmailNotExistedException::new);

        Company findCompany = companyRepository.findById(companyId)
                .orElseThrow(CompanyNotExistedException::new);

        findEmployee.updateCompany(findCompany);
    }
}
