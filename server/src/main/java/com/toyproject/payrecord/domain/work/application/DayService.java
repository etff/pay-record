package com.toyproject.payrecord.domain.work.application;

import com.toyproject.payrecord.domain.work.application.dto.DayPlanRequest;
import com.toyproject.payrecord.domain.work.domain.Day;
import com.toyproject.payrecord.domain.work.domain.DayId;
import com.toyproject.payrecord.domain.work.domain.DayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
@RequiredArgsConstructor
public class DayService {

    private final DayRepository dayRepository;

    public void createPlan(Long empId, DayPlanRequest resource) throws ParseException {
        // 202007120900
        String date = resource.getDate();

        Day day = Day.builder()
                .dayId(new DayId(empId, date))
                .startTime(resource.getStartTime())
                .endTime(resource.getEndTime()).build();

        dayRepository.save(day);
    }
}
