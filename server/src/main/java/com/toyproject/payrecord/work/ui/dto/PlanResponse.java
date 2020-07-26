package com.toyproject.payrecord.work.ui.dto;

import com.toyproject.payrecord.employee.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanResponse {
    private String date;
    private int startTime;
    private int endTime;
    private Employee employee;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
