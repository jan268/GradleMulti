package com.example.c.service;

import com.example.library.dto.Human;
import lombok.extern.slf4j.Slf4j;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ReadService {

    public Human read() {
        ClientCache cache = new ClientCacheFactory()
                .addPoolLocator("192.168.1.105", 10334)
                .create();
        Region<Long, Human> region = cache.<Long, Human>
                createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
                .create("baeldung");

        Human record = region.get(1L);
        log.info(record.toString());
        log.info(region.get(2L).toString());
        return record;
    }

    public void send() {
        new RestTemplate().postForObject("http://localhost:8093/api/v4/load", read(), Human.class);
    }
}
