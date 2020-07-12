package com.toyproject.payrecord.config.auth.ui;

import com.toyproject.payrecord.config.auth.dto.AuthRequest;
import com.toyproject.payrecord.config.auth.dto.AuthResponse;
import com.toyproject.payrecord.domain.employee.application.dto.EmpResponseDto;
import com.toyproject.payrecord.domain.employee.application.EmpService;
import com.toyproject.payrecord.global.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final EmpService empService;


    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> create(
            @RequestBody AuthRequest resource
    ) throws URISyntaxException {

        String email = resource.getEmail();
        String password = resource.getPassword();
        EmpResponseDto emp = empService.authenticate(email, password);

        String accessToken = jwtUtil.createToken(emp.getId(), emp.getEmail());

        String url = "/session";

        return ResponseEntity.created(new URI(url)).body(
                AuthResponse.builder()
                        .accessToken(accessToken)
                        .build());
    }

}
