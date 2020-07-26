package com.toyproject.payrecord.work.ui.dto;

import lombok.Data;

@Data
public class TimelineResponse {
    private Long id;
    private String event;
    private String createdAt;

    public TimelineResponse(Long id, String event, String createdAt) {
        this.id = id;
        this.event = event;
        this.createdAt = createdAt;
    }
}
