package com.toyproject.payrecord.work.domain;

import java.util.Arrays;

public enum WorkType {
    START("출근"), END("퇴근");

    private String value;

    WorkType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static WorkType of(String type) {
        return Arrays.stream(values())
                .filter(v -> v.getValue().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
