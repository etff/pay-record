package com.toyproject.payrecord.domain.work.domain;


import com.toyproject.payrecord.domain.employee.domain.Employee;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;


@Entity
@Table(name = "day")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Day {

    @EmbeddedId
    private DayId dayId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "employee_id")
    @MapsId("employee_id")
    private Employee employee;

    @ApiModelProperty(value = "계획시작시간", required = false)
    private String startTime;
    @ApiModelProperty(value = "계획종료시간", required = false)
    private String endTime;

    @ApiModelProperty(value = "지각여부", required = false)
    private boolean isLate;

    @ApiModelProperty(value = "근무실제시작시간", required = false)
    private String workStartTime;
    @ApiModelProperty(value = "근무실제종료시간", required = false)
    private String workEndTime;

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

}
