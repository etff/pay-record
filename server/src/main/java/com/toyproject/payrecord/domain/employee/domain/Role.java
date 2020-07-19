package com.toyproject.payrecord.domain.employee.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Embeddable;

public enum Role implements GrantedAuthority {
    ROLE_MANAGER, ROLE_EMPLOYEE;

    public String getAuthority() {
        return name();
    }
}
