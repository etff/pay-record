package com.toyproject.payrecord.domain.work.application;

import com.toyproject.payrecord.domain.work.application.dto.PlanRequest;
import com.toyproject.payrecord.domain.work.domain.Day;
import com.toyproject.payrecord.domain.work.domain.DayId;
import com.toyproject.payrecord.domain.work.domain.DayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DayService {

    private final DayRepository dayRepository;
    private int planStartHour;
    private int planStartMin;
    private int planEndHour;
    private int planEndMin;

    public void createPlan(Long empId, PlanRequest resource) throws ParseException {
        String date = resource.getDate();
        int startTime = 0;
        int endTime = 0;

        startTime = getStartTime(resource);
        endTime = getEndTime(resource);

        Day saveDay = Day.builder().dayId(new DayId(empId, date))
                .startTime(startTime)
                .endTime(endTime)
                .build();

        dayRepository.save(saveDay);
    }

    private int getStartTime(PlanRequest resource) {
        if (Objects.nonNull(resource.getStartTime())) {
            planStartHour = Integer.parseUnsignedInt(resource.getStartTime().substring(0, 2)) * 60;
            planStartMin = Integer.parseUnsignedInt(resource.getStartTime().substring(2, 4));
        }
        return planStartHour + planStartMin;
    }

    private int getEndTime(PlanRequest resource) {
        if (Objects.nonNull(resource.getEndTime())) {
            planEndHour = Integer.parseUnsignedInt(resource.getEndTime().substring(0, 2)) * 60;
            planEndMin = Integer.parseUnsignedInt(resource.getEndTime().substring(2, 4));
        }
        return planEndHour + planEndMin;
    }
}
