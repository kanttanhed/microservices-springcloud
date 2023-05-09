package com.github.kanttanhed.eurekaserver;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;

@EnableWebSecurity
public class SecurityConfig   {

    @Bean
    public HttpBasicConfigurer<HttpSecurity> filterChain(HttpSecurity http) throws Exception {
        return http
               .csrf()
               .disable()
               .authorizeRequests()
               .anyRequest().authenticated()
               .and()
               .httpBasic();

    }
}
