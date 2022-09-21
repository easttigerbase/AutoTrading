package com.tigerbase.autotrading.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("trading")
@Getter
@Setter
public class AuthProperties {

    private String key ;
    private String secret ;
    private String id ;
    private String passwd;

}
