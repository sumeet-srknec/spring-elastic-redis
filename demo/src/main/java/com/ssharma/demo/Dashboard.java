package com.ssharma.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/dash")
public class Dashboard {
    @GetMapping("/hello")
    public String helloString(@RequestParam String param) {
        return String.format("Hi, %s", param);
    }
    
}
