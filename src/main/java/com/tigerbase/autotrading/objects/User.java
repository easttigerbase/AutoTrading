package com.tigerbase.autotrading.objects;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class User {

    @Value("${trading.key}")
    private String appkey;
    @Value("${trading.secret}")
    private String appsecret;
    @Value("${trading.id}")
    private String tr_id;
    @Value("${trading.passwd")
    private String tr_passwd;
    @Value("${api.url}")
    private String apiURL;








}
