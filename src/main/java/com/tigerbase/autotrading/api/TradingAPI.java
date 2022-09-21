package com.tigerbase.autotrading.api;


import com.tigerbase.autotrading.annotation.Auth;
import com.tigerbase.autotrading.objects.Order;
import com.tigerbase.autotrading.objects.Token;
import com.tigerbase.autotrading.service.Trading.InquireService;
import com.tigerbase.autotrading.service.Trading.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TradingAPI {


    @Autowired
    private InquireService inquireService;

    @RequestMapping(value = "/inquirePrice")
    public Object inquirePrice(){

        Order order = new Order();
        return inquireService.inquirePrice();
    }


    @RequestMapping(value = "/myaccount")
    public Object getmyaccount(){

        return inquireService.getMyAccount();
    }


    @RequestMapping(value = "/test")
    public String gettest(){

        return "test";
    }
}
