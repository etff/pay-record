package com.toyproject.payrecord.domain.work.application.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class PlanRequest {

    @NotEmpty
    @Pattern(regexp="([12]\\d{3}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01]))")
    private String date;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{4}$")
    private String startTime;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{4}$")
    private String endTime;
}
