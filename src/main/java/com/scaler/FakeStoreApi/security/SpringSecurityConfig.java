package com.scaler.FakeStoreApi.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers("/products/all").hasAuthority("ADMIN")
//                        .requestMatchers("/messages/**").access(hasScope("message:read"))
                        .anyRequest().permitAll()


                ).oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults())
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(new CustomJwtAuthenticationConverter())
                        )
                );
        return http.build();
    }


}
