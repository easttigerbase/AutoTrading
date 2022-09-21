package com.tigerbase.autotrading.service.Trading;

import com.tigerbase.autotrading.objects.Order;
import com.tigerbase.autotrading.objects.User;
import com.tigerbase.autotrading.service.Auth.HanKookAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Consumer;

@Service
public class OrderService {

    public final User user;
    public final WebClient webClient;
    public final HanKookAuth hanKookAuth;

    @Autowired
    public OrderService(User user, WebClient webClient, HanKookAuth hanKookAuth) {
        this.user = user;
        this.webClient = webClient;
        this.hanKookAuth = hanKookAuth;
    }


    public ResponseEntity<Object> orderStock(Order order){
        String url = "/uapi/domestic-stock/v1/trading/order-cash";
        Consumer<HttpHeaders> headersConsumer =new Consumer<HttpHeaders>() {
            @Override
            public void accept(HttpHeaders httpHeaders) {

                httpHeaders.add("tr_id",user.getTr_id());
                httpHeaders.setBearerAuth(hanKookAuth.getToken().getAccess_token());
                //httpHeaders.add("hashkey",getHashkey(order));
            }
        };
       return webClient.post()
                .headers(headersConsumer)
                .bodyValue(order)
                .retrieve()
                .toEntity(Object.class)
                .block();
    }

}
