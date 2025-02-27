package com.palestra.allenamentoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disabilita la protezione CSRF per le API (può essere riabilitata per altre applicazioni)
                .csrf().disable()

                // Cambiato da `authorizeRequests()
                .authorizeHttpRequests()

                // Richiedi autenticazione per tutte le altre richieste
                .anyRequest().permitAll();
        return http.build();
    }
}
