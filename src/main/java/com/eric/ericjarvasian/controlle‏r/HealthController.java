package com.eric.ericjarvasian.controlle‏r;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping("")
    public String health() {
        System.out.println("I'm healthy");
        return "I'm healthy";
    }
}
