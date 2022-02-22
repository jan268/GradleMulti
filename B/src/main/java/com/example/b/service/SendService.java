package com.example.b.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendService {

    private final ReceiveService receiveService;

    public void sendToRegion() {


    }

    public void store() {
        ClientCache cache = new ClientCacheFactory()
                .addPoolLocator("192.168.1.105", 10334)
                .create();
        Region<Long, String> region = cache.<Long, String>
                createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
                .create("baeldung");

        region.put(1L, "Jan");
        region.put(2L, "Natalia");
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
