package com.toyproject.payrecord.work.application;

import com.toyproject.payrecord.employee.domain.Employee;
import com.toyproject.payrecord.employee.domain.EmployeeRepository;
import com.toyproject.payrecord.work.domain.Day;
import com.toyproject.payrecord.work.domain.DayRepository;
import com.toyproject.payrecord.work.domain.keys.DayId;
import com.toyproject.payrecord.work.ui.dto.PlanRequest;
import com.toyproject.payrecord.work.ui.dto.PlanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DayService {

    private final DayRepository dayRepository;
    private final EmployeeRepository employeeRepository;
    private int planStartHour;
    private int planStartMin;
    private int planEndHour;
    private int planEndMin;

    public void createPlan(String email, PlanRequest resource) throws ParseException {
        String date = resource.getDate();
        Employee employee = employeeRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        int startTime = 0;
        int endTime = 0;

        startTime = getStartTime(resource);
        endTime = getEndTime(resource);

        Day saveDay = Day.builder().dayId(new DayId(employee.getId(), date))
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

    public PlanResponse getPlanByEmail(String email, String date) {
        Employee employee = employeeRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        DayId dayId = new DayId(employee.getId(), date);

        Day day = dayRepository.findById(dayId).orElseThrow(IllegalArgumentException::new);
        return PlanResponse.builder()
                .date(date)
                .startTime(day.getStartTime())
                .endTime(day.getEndTime())
                .build();
    }
}
