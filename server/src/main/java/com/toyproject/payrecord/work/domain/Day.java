package com.toyproject.payrecord.work.domain;


import com.toyproject.payrecord.config.BaseEntity;
import com.toyproject.payrecord.employee.domain.Employee;
import com.toyproject.payrecord.work.domain.keys.DayId;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;


@Entity
@Table(name = "day")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Day extends BaseEntity {

    @EmbeddedId
    private DayId dayId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "employee_id")
    @MapsId("employee_id")
    private Employee employee;

    @ApiModelProperty(value = "계획시작시간", required = false)
    private int startTime;
    @ApiModelProperty(value = "계획종료시간", required = false)
    private int endTime;

    @ApiModelProperty(value = "지각여부", required = false)
    private boolean isLate;

    @ApiModelProperty(value = "근무실제시작시간", required = false)
    private int workStartTime;
    @ApiModelProperty(value = "근무실제종료시간", required = false)
    private int workEndTime;

    @ApiModelProperty(value = "계획시간", required = false)
    private int planTime;

    @Transient
    @ApiModelProperty(value = "남은시간", required = false)
    private int remainTime;

    @ApiModelProperty(value = "시간급여", required = false)
    private int timePay;

    @ApiModelProperty(value = "해당월", required = false)
    private String month;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    private List<Timeline> timelines = new ArrayList<>();

    public Day(DayId dayId, int startTime, int endTime, int timePay) {
        validate(startTime, endTime, timePay);
        this.dayId = dayId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timePay = timePay;
        this.planTime = endTime - startTime;
    }

    private void validate(int startTime, int endTime, int timePay) {
        if (startTime <= 0 || endTime <= 0 || timePay <= 0) {
            throw new IllegalArgumentException("0보다 더 작은 값이 들어올 수 없습니다.");
        }

        if (endTime - startTime < 0) {
            throw new IllegalArgumentException("시작 시간이 종료시간보다 클 수 없습니다.");
        }
    }

    public void addEvent(Timeline timeline) {
        /**
         * 연관관계 맵핑
         */
        timelines.add(timeline);
        timeline.setDay(this);
        setWorkTime(timeline);
    }

    private void setWorkTime(Timeline timeline) {
        if (isStartWorkTime(timeline)) {
            this.workStartTime = LocalDateTime.now().getHour() * 60 + LocalDateTime.now().getMinute();
        }

        if (isEndWorkTime(timeline)) {
            this.workEndTime = LocalDateTime.now().getHour() * 60 + LocalDateTime.now().getMinute();
        }
    }

    private boolean isStartWorkTime(Timeline timeline) {
        return WorkType.of(timeline.getEvent()).equals(WorkType.START);
    }

    private boolean isEndWorkTime(Timeline timeline) {
        return WorkType.of(timeline.getEvent()).equals(WorkType.END);
    }

}
