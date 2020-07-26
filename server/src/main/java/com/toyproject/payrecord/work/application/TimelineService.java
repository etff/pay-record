package com.toyproject.payrecord.work.application;

import com.toyproject.payrecord.work.domain.DayRepository;
import com.toyproject.payrecord.work.domain.TimelineRepository;
import com.toyproject.payrecord.work.ui.dto.TimelineResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimelineService {
    private final TimelineRepository timelineRepository;
    private final DayRepository dayRepository;

    public TimelineResponse save(String email) {
        return null;
    }

}
