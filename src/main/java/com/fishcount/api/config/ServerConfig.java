package com.fishcount.api.config;

import com.fishcount.api.config.beans.RestTemplateBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ServerConfig {

    @Autowired
    protected RestTemplateBean restTemplate;

    @Value("${server.path}")
    private String serverPath;

//    @Scheduled(cron = "0 0/5 * * * *")
    public void keepAlive() {
        restTemplate.getRestConfig()
                .getForObject(serverPath, String.class);
    }

}
