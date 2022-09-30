package com.example.jwtdemo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class JwtDemoApplicationTests {
    /*
        JWT 範例
     */
    @Test
    public void testJwt(){
        try{

            Algorithm algorithm = Algorithm.HMAC256("xxxx");
            String token = JWT.create()
                    .withIssuer("888")
                    .withSubject("Rose")
                    .withIssuedAt(new Date())
                    .sign(algorithm);
            System.out.println("token:"+token);
            String [] split=token.split("\\.");
            System.out.println(new String(Base64.getUrlDecoder().decode(split[0])));
            System.out.println(Base64.getUrlDecoder().decode(split[1]));
            System.out.println(Base64.getUrlDecoder().decode(split[2]));



        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }
    }

    /*
        token驗證解析
     */
    @Test
    public void testParseToken(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSb3NlIiwiaXNzIjoiODg4IiwibmFtZSI6IkphY2siLCJsb2dvIjoieHh4IiwiaWF0IjoxNjY0NTA0OTUyfQ.IF7n1cqvHDa1nqkwq7zTD_WPoYjLvLAil4RKr1JsG4k";
        try {
            Algorithm algorithm = Algorithm.HMAC256("xxxx");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("888")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("keyId:"+jwt.getClaims());
            System.out.println("payload:" + jwt.getPayload());
            System.out.println("header:" + jwt.getHeader());
            System.out.println("name:"+jwt.getClaims().get("name"));
        }catch (JWTVerificationException e){
            //Invalid signature/claims
            e.printStackTrace();
        }

    }
    /*
        過期教驗
     */

    @Test
    public void testCreatTokenHasExp(){
        /*
            1.先建立有時間限制的Jwt
         */
//        try{
//            long now=System.currentTimeMillis();
//            //過期時間，這裡是1分後的時間整型
//            long exp=now+60*1000;
//            Algorithm algorithm = Algorithm.HMAC256("xxxx");
//            String token = JWT.create()
//                    .withIssuer("888")
//                    .withSubject("Rose")
//                    .withIssuedAt(new Date(exp))
//                    .sign(algorithm);
//            System.out.println("token:"+token);
//            String [] split=token.split("\\.");
//            System.out.println(new String(Base64.getUrlDecoder().decode(split[0])));
//            System.out.println(Base64.getUrlDecoder().decode(split[1]));
//            System.out.println(Base64.getUrlDecoder().decode(split[2]));
//
//
//        } catch (JWTCreationException exception){
//            //Invalid Signing configuration / Couldn't convert Claims.
//        }


         /*
            2.時間內才可以解析，時間外會丟excep
         */
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSb3NlIiwiaXNzIjoiODg4IiwiaWF0IjoxNjY0NDQ0ODczfQ.Nde2auKLKTx6xhIBSZMDGXSsI_IqJJ50VAzYADXoAi8";
        try {
            Algorithm algorithm = Algorithm.HMAC256("xxxx");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("888")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("keyId:"+jwt.getClaims());
            System.out.println("payload:" + jwt.getPayload());
            System.out.println("header:" + jwt.getHeader());
        }catch (JWTVerificationException e){
            //Invalid signature/claims
            e.printStackTrace();
        }





    }

    /*
        生成jwt(自訂義聲明)
        claim 會以key value的形式儲存 
     */
    @Test
    public void testJwtHasEnhancer(){
        //當前時間
        long date=System.currentTimeMillis();
        //失效時間
        long exp =date + 60*1000;

        Algorithm algorithm = Algorithm.HMAC256("xxxx");
        String token = JWT.create()
                .withIssuer("888")
                .withSubject("Rose")
                .withIssuedAt(new Date())
                .withClaim("name","Jack")
                .withClaim("logo","xxx")
                .sign(algorithm);
        System.out.println("token:"+token);

    }
}
