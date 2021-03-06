package com.toyproject.payrecord.config.auth.application;

import com.toyproject.payrecord.employee.domain.Employee;
import com.toyproject.payrecord.employee.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomUserDetailService {
    private final EmployeeRepository employeeRepository;

    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);

        if (employee == null) {
            throw new UsernameNotFoundException("user(email) name doesn't exist");
        }

        return CustomUserDetails.builder()
                .employeeId(employee.getId())
                .userName(employee.getEmail())
                .password(employee.getPassword())
                .authorities(employee.getRoles()).build();

    }
}
