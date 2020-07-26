package com.toyproject.payrecord.config.auth.ui.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
