package com.example.b.service;

import com.example.library.dto.Human;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendService {

    private final ReceiveService receiveService;

    public void store() {
        List<Human> humans = receiveService.loadFromQueue();
        ClientCache cache = new ClientCacheFactory()
                .addPoolLocator("192.168.1.105", 10334)
                .create();
        Region<Long, Human> region = cache.<Long, Human>
                createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
                .create("baeldung");

        humans.forEach(human -> region.put(human.getId(), human));
    }

    public void read() {
        ClientCache cache = new ClientCacheFactory()
                .addPoolLocator("192.168.1.105", 10334)
                .create();
        Region<Long, String> region = cache.<Long, String>
                createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
                .create("baeldung");

        log.info(region.get(1L));
        log.info(region.get(2L));
    }
}
