package com.toyproject.payrecord.config.auth.ui.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String accessToken;
}
