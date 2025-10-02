package com.senac.daht.agenda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;
    public static final String [] ENDPOINTS_PUBLIC = {
            //Usuário
            "/api/usuario/criar",
            "/api/usuario/login",
            "/api/usuario/listar",
            "/api/usuario/listarPorId/**",
            "/api/usuario/atualizar/**",
            "/api/usuario/deletar/**",

            //Personagem
            "/api/personagem/criar",
            "/api/personagem/login",
            "/api/personagem/listar",
            "/api/personagem/listarPorId/**",
            "/api/personagem/atualizar/**",
            "/api/personagem/deletar/**",

            //Missão
            "/api/missao/criar",
            "/api/missao/login",
            "/api/missao/listar",
            "/api/missao/listarPorId/**",
            "/api/missao/atualizar/**",
            "/api/missao/deletar/**",

            //Ganho
            "/api/ganho/criar",
            "/api/ganho/login",
            "/api/ganho/listar",
            "/api/ganho/listarPorId/**",
            "/api/ganho/atualizar/**",
            "/api/ganho/deletar/**",

            //Prêmio
            "/api/premio/criar",
            "/api/premio/login",
            "/api/premio/listar",
            "/api/premio/listarPorId/**",
            "/api/premio/atualizar/**",
            "/api/premio/deletar/**",

            //Tabela Prêmio
            "/api/tabelapremio/criar",
            "/api/tabelapremio/login",
            "/api/tabelapremio/listar",
            "/api/tabelapremio/listarPorId/**",
            "/api/tabelapremio/deletar/**",

            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"


    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ENDPOINTS_PUBLIC).permitAll()
                        .anyRequest().authenticated()
                ).addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}