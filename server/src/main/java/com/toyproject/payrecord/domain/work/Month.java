package com.toyproject.payrecord.domain.work;

import com.toyproject.payrecord.domain.employee.Employee;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "month")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Month {

    @EmbeddedId
    private MonthId monthId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "employee_id")
    @MapsId("employee_id")
    private Employee employee;

}
