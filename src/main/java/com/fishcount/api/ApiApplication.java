package com.fishcount.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EntityScan(basePackages = {"com.fishcount.common.model.entity",
    "com.fishcount.common.model.entity.financeiro",
    "com.fishcount.common.model.entity.financeiro.pix"
})
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
