package com.tigerbase.autotrading.objects;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Order {
    String CANO;
    String ACNT_PRDT_CD;
    String PDNO;
    String ORD_DVSN;
    String ORD_QTY;
    String ORD_UNPR;
    String ALGO_NO;
}
