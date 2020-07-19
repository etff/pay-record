package com.toyproject.payrecord.domain.work;

import com.toyproject.payrecord.domain.work.application.DayService;
import com.toyproject.payrecord.domain.work.application.dto.PlanRequest;
import com.toyproject.payrecord.domain.work.application.dto.PlanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class WorkController {
    private final DayService dayService;

    @PostMapping("/plans")
    public ResponseEntity<?> creatPlan(
            Authentication authentication,
            @Valid @RequestBody PlanRequest resource
    ) throws ParseException, URISyntaxException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails == null) {
            return ResponseEntity.badRequest().body("token error");
        }
        dayService.createPlan(userDetails.getUsername(), resource);

        String url = "/plan/" + userDetails.getUsername() + "/" + resource.getDate();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @GetMapping("/plans/{date}")
    public ResponseEntity<?> getPlan(
            Authentication authentication,
            @PathVariable("date") String date
    ) throws ParseException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails == null) {
            return ResponseEntity.badRequest().body("token error");
        }
        String email = userDetails.getUsername();

        PlanResponse plan = dayService.getPlanByEmail(email, date);

        return ResponseEntity.ok(plan);
    }


}
