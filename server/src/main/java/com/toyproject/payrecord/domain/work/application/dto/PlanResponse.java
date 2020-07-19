package com.toyproject.payrecord.domain.work.application.dto;

import com.toyproject.payrecord.domain.employee.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class PlanResponse {
    private String date;
    private int startTime;
    private int endTime;
    private Employee employee;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PlanResponse(String date, int startTime, int endTime, Employee employee, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
