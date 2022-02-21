package com.example.a.service;


import com.example.a.Human;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoadService {

    private final SendingService sendingService;

    @SneakyThrows
    public void loadByBatch(MultipartFile file) {
        try {
            List<Human> people = CSVHelper.csvToHuman(file.getInputStream());
            log.info(people.get(0).getCity());
            sendingService.sendPeople(people);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
