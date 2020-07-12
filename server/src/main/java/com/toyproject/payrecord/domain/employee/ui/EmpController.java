package com.toyproject.payrecord.domain.employee.ui;

import com.toyproject.payrecord.domain.employee.application.EmpService;
import com.toyproject.payrecord.domain.employee.domain.Employee;
import com.toyproject.payrecord.dto.SingUpRequest;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> singUp(@Valid @RequestBody SingUpRequest res) throws URISyntaxException {
        String email = res.getEmail();
        String password = res.getPassword();
        Employee emp = empService.singUp(email, password);

        URI location = new URI("/api/employees/" + emp.getId());
        return ResponseEntity.created(location).body("{}");
    }
}
