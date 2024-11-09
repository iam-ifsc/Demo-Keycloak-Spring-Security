package com.luizakuze.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//Classe que habilita a segurança do serviço web
@Configuration
@EnableWebSecurity
public class SecurityConfig{
     
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests( 
                authorizeConfig -> {
                    authorizeConfig.requestMatchers("/public").permitAll();  
                    authorizeConfig.requestMatchers("/logout").permitAll();  
                        authorizeConfig.anyRequest().authenticated(); 
                })
            .oauth2Login(Customizer.withDefaults()) // Essa linha faz a integração com OAuth2.0, cookies de sessão
            //.oauth2ResourceServer(oauth2 -> oauth2.jwt()) // Modo antigo...
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())) // Habitando Resource Server, vou escolher entre token opaco ou jwt (escolhi jwt)
            .build();
          
    }
}
