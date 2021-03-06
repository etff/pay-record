package com.toyproject.payrecord.work.application;

import com.toyproject.payrecord.employee.domain.EmployeeRepository;
import com.toyproject.payrecord.work.domain.Day;
import com.toyproject.payrecord.work.domain.DayRepository;
import com.toyproject.payrecord.work.domain.Timeline;
import com.toyproject.payrecord.work.domain.TimelineRepository;
import com.toyproject.payrecord.work.domain.keys.DayId;
import com.toyproject.payrecord.work.ui.dto.TimelineResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TimelineService {
    private final TimelineRepository timelineRepository;
    private final DayRepository dayRepository;
    private final EmployeeRepository employeeRepository;

    public TimelineResponse save(Long employeeId, String event) {
        String time = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

        DayId dayId = new DayId(employeeId, time);
        Day day = dayRepository.findById(dayId).orElseThrow(() -> new IllegalArgumentException("계획이 존재해야 합니다."));
        
        Timeline saved = timelineRepository.save(new Timeline(event));
        day.addEvent(saved);

        return new TimelineResponse(saved.getId(), saved.getEvent(), time);
    }

}
