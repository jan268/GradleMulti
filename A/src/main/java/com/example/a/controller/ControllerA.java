package com.example.a.controller;

import com.example.a.service.LoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ControllerA {

    private final LoadService loadService;

    @PostMapping("load")
    public void loadData(@RequestBody MultipartFile file) {
        loadService.loadByBatch(file);
    }
}
