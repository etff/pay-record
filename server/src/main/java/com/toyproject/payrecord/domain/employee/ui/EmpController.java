package com.toyproject.payrecord.domain.employee.ui;

import com.toyproject.payrecord.domain.employee.application.EmpService;
import com.toyproject.payrecord.domain.employee.application.dto.SignUpRequest;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = "사용자 APIs")
public class EmpController {

    private final EmpService empService;

    @PostMapping("/employees")
    public ResponseEntity<?> singUp(@Valid @RequestBody SignUpRequest res) throws URISyntaxException {
        String email = res.getEmail();
        String password = res.getPassword();
        Long empyId = empService.singUp(email, password);

        URI location = new URI("/api/employees/" + empyId);
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/employees/company")
    public ResponseEntity<?> updateCompany(
            Authentication authentication,
            @PathVariable("companyId") Long companyId
    ) {
        Claims claims = (Claims) authentication.getPrincipal();

        Long empId = claims.get("empId", Long.class);
        empService.updateCompany(empId, companyId);

        return ResponseEntity.ok("{}");
    }
}
