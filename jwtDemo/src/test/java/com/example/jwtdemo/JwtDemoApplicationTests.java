package com.example.jwtdemo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Base64;
import java.util.Date;

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

}
