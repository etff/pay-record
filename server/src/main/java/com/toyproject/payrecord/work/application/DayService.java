package com.toyproject.payrecord.work.application;

import com.toyproject.payrecord.employee.domain.Employee;
import com.toyproject.payrecord.employee.domain.EmployeeRepository;
import com.toyproject.payrecord.global.utils.TimeUtil;
import com.toyproject.payrecord.work.domain.Day;
import com.toyproject.payrecord.work.domain.DayRepository;
import com.toyproject.payrecord.work.domain.Month;
import com.toyproject.payrecord.work.domain.MonthRepository;
import com.toyproject.payrecord.work.domain.keys.DayId;
import com.toyproject.payrecord.work.domain.keys.MonthId;
import com.toyproject.payrecord.work.ui.dto.PlanRequest;
import com.toyproject.payrecord.work.ui.dto.PlanResponse;
import com.toyproject.payrecord.work.ui.dto.TimelineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DayService {
    private static final int TIME_PER_PAY = 10_000;

    private final DayRepository dayRepository;
    private final EmployeeRepository employeeRepository;
    private final MonthRepository monthRepository;

    public void createPlan(String email, PlanRequest resource) {
        String date = resource.getDate();
        Employee employee = employeeRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        int startTime = 0;
        int endTime = 0;

        startTime = getStartTime(resource);
        endTime = getEndTime(resource);
        Day saveDay = new Day(  new DayId(employee.getId(), date),
                                startTime,
                                endTime,
                                TIME_PER_PAY);

        dayRepository.save(saveDay);
        monthRepository.save(new Month(new MonthId(employee.getId(), date)));
    }

    private int getStartTime(PlanRequest resource) {
        return TimeUtil.ParseToIntTime(resource.getStartTime());
    }

    private int getEndTime(PlanRequest resource) {
        return TimeUtil.ParseToIntTime(resource.getEndTime());
    }

    @Transactional(readOnly = true)
    public PlanResponse getPlanByEmail(String email, String date) {
        Employee employee = employeeRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        DayId dayId = new DayId(employee.getId(), date);

        Day day = dayRepository.findById(dayId).orElseThrow(IllegalArgumentException::new);

        List<TimelineDto> timelineDtos = day.getTimelines().stream()
                .map(t -> new TimelineDto(t.getId(), t.getEvent(), t.getCreatedAt(), t.getUpdatedAt()))
                .collect(toList());

        return new PlanResponse(day, timelineDtos);
    }
}
