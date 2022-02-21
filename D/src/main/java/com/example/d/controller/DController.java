package com.example.d.controller;

import com.example.d.service.SendService;
import com.example.library.dto.Human;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v4/")
@RequiredArgsConstructor
public class DController {

    private final SendService sendService;

    @PostMapping("load")
    public void load(@RequestBody List<Human> people) {
        sendService.send(people);
    }

}
