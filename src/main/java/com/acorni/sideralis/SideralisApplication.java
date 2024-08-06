package com.acorni.sideralis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SideralisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SideralisApplication.class, args);
    }

}
