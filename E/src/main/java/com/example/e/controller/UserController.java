package com.example.e.controller;

import com.example.e.service.ReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v5/")
@RequiredArgsConstructor
public class UserController {

    ReadService readService;

    @GetMapping("read")
    public void read(@RequestParam List<Long> ids) {
        readService.read(ids);
    }
}
