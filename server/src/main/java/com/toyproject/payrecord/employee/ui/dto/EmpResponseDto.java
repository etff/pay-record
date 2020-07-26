package com.toyproject.payrecord.employee.ui.dto;

import com.toyproject.payrecord.employee.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpResponseDto {
    private Long id;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedTime;

    public static EmpResponseDto of(Employee employee, String token) {
        return new EmpResponseDto(
                employee.getId(),
                employee.getEmail(),
                employee.getCreatedAt(),
                employee.getUpdatedAt()
        );
    }

}