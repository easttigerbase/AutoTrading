package com.tigerbase.autotrading.service.Trading;


import com.tigerbase.autotrading.objects.Token;
import com.tigerbase.autotrading.objects.Order;
import com.tigerbase.autotrading.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class CertificationService {
    public final User user;
    public final WebClient webClient;

    @Autowired
    public CertificationService(User user, WebClient webClient) {
        this.user = user;
        this.webClient = webClient;
    }

    public String getHashkey(Order order){
        String url = "/uapi/hashkey";

        return webClient.post()
                .uri(url)
                .bodyValue(order)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public Token getAuth(){
        String url = "/oauth2/tokenP";

        Map<String,String> params = new HashMap<>();
        params.put("grant_type","client_credentials");
        params.put("appkey",user.getAppkey());
        params.put("appsecret",user.getAppsecret());

        return webClient.post()
                .uri(UriComponentsBuilder
                        .fromUriString(url)
                        .toUriString()
                )
                .body(BodyInserters.fromValue(params))
                .retrieve()
                .bodyToMono(Token.class)
                .block();
    }
}
