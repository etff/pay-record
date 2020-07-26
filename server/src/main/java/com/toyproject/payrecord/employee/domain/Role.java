package com.toyproject.payrecord.employee.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_MANAGER, ROLE_EMPLOYEE;

    public String getAuthority() {
        return name();
    }
}
