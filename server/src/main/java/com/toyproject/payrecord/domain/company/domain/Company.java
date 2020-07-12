package com.toyproject.payrecord.domain.company.domain;

import com.toyproject.payrecord.config.BaseEntity;
import com.toyproject.payrecord.domain.employee.domain.Employee;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "company", fetch = LAZY)
    private Employee employee;
}
