package com.toyproject.payrecord.work.domain;

public enum WorkType {
    START("출근"), END("퇴근");

    private String value;

    WorkType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
