package com.toyproject.payrecord.work.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TimelineDto {
    private Long id;
    private String event;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
