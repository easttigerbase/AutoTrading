package com.tigerbase.autotrading.config;

import com.tigerbase.autotrading.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Consumer;

@Configuration
public class WebClientConfigurer {

    private WebClient client;

    private User user;

    @Bean
    public WebClient WebClientConfigurer(User user)  {
        this.user = user;
        Consumer<HttpHeaders> httpHeadersConsumer = new Consumer<HttpHeaders>() {
            @Override
            public void accept(HttpHeaders header) {
                header.add("content-type","application/json;charset=UTF-8");
                header.add("appkey",user.getAppkey());
                header.add("appsecret",user.getAppsecret());
            }

        };

        this.client = WebClient.builder()
                .baseUrl(user.getApiURL())
                .defaultHeaders(httpHeadersConsumer)
            .build();
        return this.client;
    }
}
