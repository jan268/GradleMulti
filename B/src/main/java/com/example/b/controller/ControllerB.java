package com.example.b.controller;

import com.example.b.service.ReceiveService;
import com.example.b.service.SendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/")
@RequiredArgsConstructor
public class ControllerB {

    private final ReceiveService receiveService;
    private final SendService sendService;

    @GetMapping("load")
    public void loadQueue() {
        receiveService.loadFromQueue();
    }

    @GetMapping("store")
    public void store() {
        sendService.store();
    }

    @GetMapping("read")
    public void read() {
        sendService.read();
    }
}
