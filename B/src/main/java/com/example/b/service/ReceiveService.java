package com.example.b.service;

import com.example.library.dto.Human;
import lombok.extern.slf4j.Slf4j;
import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ChronicleQueueBuilder;
import net.openhft.chronicle.ExcerptTailer;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReceiveService {

    public void loadFromQueue() {
        try {
            File queueDir = new File("chronicle-queue-send");
            Chronicle chronicle = ChronicleQueueBuilder.indexed(queueDir).build();
            ExcerptTailer tailer = chronicle.createTailer();
            log.info("Chronicle size: " + chronicle.size());
            List<Human> objects = new ArrayList<>();
            while (tailer.nextIndex()) {
                Human human1 = tailer.readInstance(Human.class, new Human());
                objects.add(human1);
                tailer.finish();
                log.info("Current index: " + tailer.index());
            }

            log.info("Size of tailer: " + tailer.size());
            log.info("Index of Start: " + tailer.toStart().index());
            log.info("Index of End: " + tailer.toEnd().index());
            log.info("Value of start: " + tailer.toStart().readLong());
            log.info("Value of end: " + tailer.toEnd().readLong());

            log.info("Size: " + objects.size());
            objects.forEach(l -> log.info(l.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
