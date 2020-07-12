package com.toyproject.payrecord.domain.employee.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotNull
    @Email
    private String email;

    private String password;

}
