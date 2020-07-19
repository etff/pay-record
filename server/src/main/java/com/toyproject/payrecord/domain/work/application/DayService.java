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

        //Day day = new Day(new DayId(empId, date), resource.getStartTime(), resource.getEndTime());


        //dayRepository.save(day);
    }
}
