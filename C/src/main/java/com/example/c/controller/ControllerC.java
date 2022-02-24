package com.example.c.controller;

import com.example.c.service.ReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/")
@RequiredArgsConstructor
public class ControllerC {

    private final ReadService readService;

    @GetMapping("read")
    public void read() {
        readService.read();
    }

    @GetMapping("send")
    public void send() {
        readService.send();
    }
}
