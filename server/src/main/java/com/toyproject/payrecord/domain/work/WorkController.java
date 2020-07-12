package com.toyproject.payrecord.domain.work;

import com.toyproject.payrecord.domain.employee.application.dto.SignUpRequest;
import com.toyproject.payrecord.domain.work.application.DayService;
import com.toyproject.payrecord.domain.work.application.dto.DayPlanRequest;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class WorkController {

    private final DayService dayService;

    public ResponseEntity<?> creatPlan(
            Authentication authentication,
            @RequestBody DayPlanRequest resource
    ) throws ParseException {
        Claims claims = (Claims) authentication.getPrincipal();
        if (claims == null) {
            return ResponseEntity.badRequest().body("token error");
        }
        Long empId = claims.get("empId", Long.class);

        dayService.createPlan(empId, resource);

        return ResponseEntity.ok("{}");
    }
}
