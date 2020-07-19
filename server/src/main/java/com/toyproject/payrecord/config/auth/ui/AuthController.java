package com.toyproject.payrecord.config.auth.ui;

import com.toyproject.payrecord.config.auth.dto.AuthRequest;
import com.toyproject.payrecord.config.auth.dto.AuthResponse;
import com.toyproject.payrecord.config.auth.jwt.JwtTokenProvider;
import com.toyproject.payrecord.domain.employee.application.dto.EmpResponseDto;
import com.toyproject.payrecord.domain.employee.application.EmpService;
import com.toyproject.payrecord.global.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final EmpService empService;

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> create(
            @RequestBody AuthRequest resource
    ) throws URISyntaxException {

        String email = resource.getEmail();
        String password = resource.getPassword();

        String accessToken = empService.authenticate(email, password);

        String url = "/auth";

        return ResponseEntity.created(new URI(url)).body(
                AuthResponse.builder()
                        .accessToken(accessToken)
                        .build());
    }

}
