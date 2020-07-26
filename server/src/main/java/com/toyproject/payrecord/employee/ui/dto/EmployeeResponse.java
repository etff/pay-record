package com.toyproject.payrecord.employee.ui.dto;

import com.toyproject.payrecord.employee.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedTime;

    public static EmployeeResponse of(Employee employee, String token) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getEmail(),
                employee.getCreatedAt(),
                employee.getUpdatedAt()
        );
    }

}
