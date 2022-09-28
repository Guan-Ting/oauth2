package com.example.oauth2.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public  void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //客戶端ID
        clients.inMemory()
                //客戶端ID
                .withClient("client")
                //密鑰
                .secret(passwordEncoder.encode("112233"))
                //重導向
                .redirectUris("http://www.baidu.com")
                //授權範圍
                .scopes("all")
                /*
                授權類型
                 */
                .authorizedGrantTypes("authorization_code");
    }
}
