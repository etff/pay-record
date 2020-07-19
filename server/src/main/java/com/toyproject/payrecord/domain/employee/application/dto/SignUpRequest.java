package com.toyproject.payrecord.domain.employee.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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

    @Length(min = 4, max = 15)
    private String password;

}
