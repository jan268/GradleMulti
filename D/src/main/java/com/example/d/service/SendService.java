package com.example.d.service;

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
public class SendService {

    public void send(List<Human> people) {
        File file = new File("map2.dat");
        try {
            ChronicleMap<LongValue, CharSequence> humanMap = ChronicleMap
                    .of(LongValue.class, CharSequence.class)
                    .name("human-map")
                    .entries(5)
                    .averageValue("John")
                    .createPersistedTo(file);

            people.forEach(human -> {
                LongValue qatarKey = Values.newHeapInstance(LongValue.class);
                qatarKey.setValue(human.getId());
                humanMap.put(qatarKey, human.getName());
            });
            humanMap.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
