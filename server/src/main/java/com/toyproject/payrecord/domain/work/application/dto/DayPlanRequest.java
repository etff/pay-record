package com.toyproject.payrecord.domain.work.application.dto;

import lombok.Data;

@Data
public class DayPlanRequest {

    private String date;
    private String startTime;
    private String endTime;
}
