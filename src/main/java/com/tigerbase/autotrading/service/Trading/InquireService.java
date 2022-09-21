package com.tigerbase.autotrading.service.Trading;

import com.tigerbase.autotrading.annotation.Auth;
import com.tigerbase.autotrading.objects.Token;
import com.tigerbase.autotrading.objects.User;
import com.tigerbase.autotrading.service.Auth.HanKookAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.function.Consumer;

@Service
public class InquireService {
    public final User user;
    public final WebClient webClient;
    public final HanKookAuth hanKookAuth;
    @Autowired
    public InquireService(User user, WebClient webClient, HanKookAuth hanKookAuth) {
        this.user = user;
        this.webClient = webClient;
        this.hanKookAuth = hanKookAuth;
    }
    public Object getMyAccount(){
        String url = "/uapi/domestic-stock/v1/trading/inquire-balance";

        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
                map.add("CANO","63533829");
                map.add("ACNT_PRDT_CD","01");
                //시간외단일가여부
                map.add("AFHR_FLPR_YN","N");
                map.add("OFL_YN","N");
                map.add("INQR_DVSN","01");
                map.add("UNPR_DVSN","01");
                map.add("FUND_STTL_ICLD_YN","N");
                map.add("FNCG_AMT_AUTO_RDPT_YN","N");
                map.add("PRCS_DVSN","01");
                map.add("CTX_AREA_FK100","");
                map.add("CTX_AREA_NK100","");

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path(url)
                        .queryParams(map)
                        .build(url))
                .headers(httpHeaders -> httpHeaders.setBearerAuth(hanKookAuth.getToken().getAccess_token()))
                .retrieve()
                .toEntity(Object.class);
    }

    public Object inquirePrice(){
        String url = "/uapi/domestic-stock/v1/quotations/inquire-price";

        Consumer<HttpHeaders> consumer = new Consumer<HttpHeaders>() {
            @Override
            public void accept(HttpHeaders httpHeaders) {
                httpHeaders.setBearerAuth(hanKookAuth.getToken().getAccess_token());
                httpHeaders.add("tr_id","FHKST01010100");
            }
        };

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();

        params.add("FID_COND_MRKT_DIV_CODE","주식");
        params.add("FID_INPUT_ISCD","068270");

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(url)
                        .queryParams(params)
                        .build(url))
                .headers(consumer)
                .retrieve().
                toEntity(Object.class);

    }


}
