package com.toyproject.payrecord.work.ui.dto;

import com.toyproject.payrecord.work.domain.Day;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanResponse {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    private String date;
    private int startTime;
    private int endTime;
    private String email;
    private List<TimelineDto> timelines;
    private Long remainTimePercent;
    private int wage;
    private int planTime;

    public PlanResponse(Day day, List<TimelineDto> timelineDto) {
        this.date = day.getDayId().getDate();
        this.startTime = day.getStartTime();
        this.endTime = day.getEndTime();
        this.email = day.getEmployee().getEmail();
        this.timelines = timelineDto;
        this.remainTimePercent = this.calculateRemainPercent(day);
        this.wage = this.calculateWage(day);
        this.planTime = day.getPlanTime();
    }

    /**
     * 남은 시간 비율 : 남은 시간 / 근무시간 * 100
     * @param day
     * @return
     */
    public Long calculateRemainPercent(Day day) {
        Long workPercent = Math.round(calculateRemainTime(day) * 100.0 / day.getPlanTime());
        return workPercent;
    }

    /**
     * 근무시간 = 계획시간 - 일한시간
     * 계획시간 - 남은 시간 = 근무시간
     * @param day
     * @return
     */
    public int calculateWage(Day day) {
        int workDuration = day.getPlanTime() - calculateRemainTime(day);
        return (workDuration / 60 ) * day.getTimePay();
    }

    /**
     * 오늘 임금을 계산할 경우에는 현재 근무 시간 대비 임금을 준다
     * 과거 일자의 임금을 보여줄 경우 근무 시간을 구한다. * wage 단, 계획시간을 초과하지않는다!
     * @param day
     * @return
     */
    private int calculateRemainTime(Day day) {
        LocalDateTime now = LocalDateTime.now();
        LocalDate saved = LocalDate.parse(day.getDayId().getDate(), formatter);
        LocalDate current = LocalDate.now();
        int intNow = now.getHour() * 60 + now.getMinute();
        int remain = 0;
        if (saved.isEqual(current) && intNow <= day.getEndTime()) {
            remain = day.getPlanTime() - (intNow - day.getWorkStartTime());
        }

        if (saved.isBefore(current)) {
            remain = day.getPlanTime() - (day.getWorkEndTime() - day.getWorkStartTime());
        }
        return remain;
    }

}
