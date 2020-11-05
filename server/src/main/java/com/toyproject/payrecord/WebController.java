package com.toyproject.payrecord;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping({"/", "/error"})
    public String index() {
        return "index.html";
    }
}
