package com.example.e.service;

import com.example.library.dto.Human;
import lombok.extern.slf4j.Slf4j;
import net.openhft.chronicle.core.values.LongValue;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.values.Values;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class ReadService {

    public void read(List<Long> ids) {
        File file = new File("map.dat");
        try {
            ChronicleMap<LongValue, Human> humanMap = ChronicleMap
                    .of(LongValue.class, Human.class)
                    .name("human-map")
                    .entries(5)
                    .averageValue(new Human())
                    .createPersistedTo(file);
            ids.forEach(id -> {
                LongValue key = Values.newHeapInstance(LongValue.class);
                key.setValue(id);
                log.info(String.valueOf(humanMap.get(key)));
            });
            humanMap.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
