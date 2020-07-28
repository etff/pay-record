package com.toyproject.payrecord.company.domain;

import com.toyproject.payrecord.config.BaseEntity;
import com.toyproject.payrecord.employee.domain.Employee;
import com.toyproject.payrecord.work.domain.Timeline;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "company")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company extends BaseEntity {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;

    @Column(name = "company_name")
    private String name;

    @OneToMany(mappedBy = "company")
    @Setter
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        /**
         * 연관관계 맵핑
         */
        employees.add(employee);
        employee.setCompany(this);
    }
}
