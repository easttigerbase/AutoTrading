package com.tigerbase.autotrading.service.Auth;


import com.tigerbase.autotrading.objects.Token;
import com.tigerbase.autotrading.service.Trading.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HanKookAuth {

    private final Token token ;
    private final CertificationService certificationService;

    @Autowired
    private HanKookAuth(CertificationService certificationService, Token token ) {
        this.certificationService = certificationService;
        this.token = token;
        //com/example/aop/controller 패키지 하위 클래스들 전부 적용하겠다고 지점 설정
        //@Pointcut("execution(* com.tigerbase.autotrading.service..*.*(..))")
        this.generateToken();
    }
    @Scheduled(cron = "0 0 8 * * *")
    private void generateToken(){
        Token new_token =certificationService.getAuth();;
        token.setToken_type(new_token.getToken_type());
        token.setExpires_in(new_token.getExpires_in());
        token.setAccess_token(new_token.getAccess_token());
    }

    public Token getToken(){
        if(this.token==null){
            generateToken();
        }
        return this.token;
    }
    /*
    @Around("@annotation(com.tigerbase.autotrading.annotation.Auth)")
    private Object getAuthToken(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("AOP AUTH");

        //<200,{access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMßiJ9.eyJzdWIiOiJ0b2tlbiIsImF1ZCI6ImE1M2I1MzhjLWJhZGEtNGYyYS05MDhlLTI5OWQyOWM0ZjUxZSIsImlzcyI6InVub2d3IiwiZXhwIjoxNjYwODMxODUyLCJpYXQiOjE2NjA3NDU0NTIsImp0aSI6IlBTTmI1d2ZIeUlDak1pYlhxb2pIQ0ROWkxydkZ2ZTdKc0piZCJ9.EfHccWoPb7XWtn9TUACVQ_fr85SisLgnQcj51tqqw47NX-qoxr2ojG3dJ8D5EhPYpmgZ2ReKMdRmQWO_8AsJYw, token_type=Bearer, expires_in=86400},[Date:"Wed, 17 Aug 2022 14:10:52 GMT", X-Content-Type-Options:"nosniff", X-ORACLE-DMS-ECID:"005tb1ijqEbFw0zzzR06zzEjX2E0jYYz30000v_003Pk6", X-ORACLE-DMS-RID:"0:1", X-XSS-Protection:"1; mode=block", Content-Type:"application/json", content-length:"388"]>
        Object resultObj = joinPoint.proceed(new Object[] { this.token });
        return resultObj;
    }
    */







}
