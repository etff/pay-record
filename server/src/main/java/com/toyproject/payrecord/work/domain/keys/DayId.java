package com.toyproject.payrecord.work.domain.keys;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
public class DayId implements Serializable {

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "date")
    private String date;

    public DayId(Long employeeId, String date) {
        this.employeeId = employeeId;
        this.date = date;
    }
}
