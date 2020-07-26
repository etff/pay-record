package com.toyproject.payrecord.work.domain.keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class MonthId implements Serializable {

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "month")
    private String month;
}

