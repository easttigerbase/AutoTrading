package com.tigerbase.autotrading.objects;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Token {
    private  String access_token ;
    private  String token_type ;
    private  String expires_in ;
    private  String duetime ;
}
