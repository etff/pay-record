package com.toyproject.payrecord.employee.ui;

import com.toyproject.payrecord.employee.application.EmployeeService;
import com.toyproject.payrecord.employee.ui.dto.SignUpRequest;
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
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<?> singUp(@Valid @RequestBody SignUpRequest res) throws URISyntaxException {
        String email = res.getEmail();
        String password = res.getPassword();
        Long empyId = employeeService.singUp(email, password);

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
        employeeService.updateCompany(empId, companyId);

        return ResponseEntity.ok("{}");
    }
}
