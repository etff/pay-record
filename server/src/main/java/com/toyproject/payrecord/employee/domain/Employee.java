package com.toyproject.payrecord.employee.domain;

import com.toyproject.payrecord.config.BaseEntity;
import com.toyproject.payrecord.company.domain.Company;
import com.toyproject.payrecord.global.utils.StringUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "employee")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee extends BaseEntity {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "이메일", required = true)
    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @ApiModelProperty(hidden = true)
    @Column(name = "password", length = 255, nullable = true)
    private String password;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ApiModelProperty(value = "ROLE", required = false)
    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

    public Employee(String email, String password) {
        validate(email, password);
        this.email = email;
        this.password = password;
    }

    private void validate(String email, String password) {
        StringUtil.validateEmail(email);
        StringUtil.validatePassword(password);
    }

    public void updateCompany(Company company) {
        this.company = company;
        company.setEmployee(this);
    }
}
