package com.toyproject.payrecord.work;

import com.toyproject.payrecord.config.auth.application.CustomUserDetails;
import com.toyproject.payrecord.work.application.DayService;
import com.toyproject.payrecord.work.application.TimelineService;
import com.toyproject.payrecord.work.domain.WorkType;
import com.toyproject.payrecord.work.ui.dto.PlanRequest;
import com.toyproject.payrecord.work.ui.dto.PlanResponse;
import com.toyproject.payrecord.work.ui.dto.TimelineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    private final TimelineService timelineService;

    @PostMapping("/plans")
    public ResponseEntity<?> creatPlan(
            Authentication authentication,
            @Valid @RequestBody PlanRequest resource
    ) throws ParseException, URISyntaxException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        dayService.createPlan(userDetails.getEmployeeId(), resource);

        String url = "/plan/" + userDetails.getUsername() + "/" + resource.getDate();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @GetMapping("/plans/{date}")
    public ResponseEntity<?> getPlan(
            Authentication authentication,
            @PathVariable("date") String date
    ) throws ParseException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        PlanResponse plan = dayService.getPlan(userDetails.getEmployeeId(), date);

        return ResponseEntity.ok(plan);
    }

    @PostMapping("/timelines/work")
    public ResponseEntity<?> startWork(
            Authentication authentication,
            @RequestParam WorkType workType
    ) throws ParseException, URISyntaxException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        TimelineResponse saved = timelineService.save(userDetails.getEmployeeId(), workType.getValue());
        String url = "/timelines/" + saved.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }


}
