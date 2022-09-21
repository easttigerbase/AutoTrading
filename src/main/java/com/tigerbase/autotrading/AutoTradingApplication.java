package com.tigerbase.autotrading;

import com.tigerbase.autotrading.config.AuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@PropertySource("classpath:/properties/stockAuth.properties")
public class AutoTradingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoTradingApplication.class, args);
    }

}
