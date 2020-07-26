package com.toyproject.payrecord.work.domain;


import com.toyproject.payrecord.config.BaseEntity;
import com.toyproject.payrecord.employee.domain.Employee;
import com.toyproject.payrecord.work.domain.keys.DayId;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


import javax.persistence.*;

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

    public void addEvent(Timeline timeline) {
        timelines.add(timeline);
        timeline.setDay(this);
    }

}
