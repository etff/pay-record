package com.toyproject.payrecord.domain.employee.domain;

import com.toyproject.payrecord.config.BaseEntity;
import com.toyproject.payrecord.domain.company.domain.Company;
import com.toyproject.payrecord.global.utils.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ApiModelProperty(value = "fcm토큰", required = false)
    @Column(name = "fcm_token", length = 255, nullable = true)
    private String fcmToken;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Employee(String email, String password) {
        this(email,password, null);
    }

    public Employee(String email, String password, String fcmToken) {
        validate(email, password);
        this.email = email;
        this.password = password;
        this.fcmToken = fcmToken == null ? null : fcmToken;
    }

    private void validate(String email, String password) {
        StringUtils.validateEmail(email);
        StringUtils.validatePassword(password);
    }

}
