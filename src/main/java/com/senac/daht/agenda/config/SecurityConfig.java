package com.senac.daht.agenda.config;

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
                // Desabilita a proteção CSRF (comum em APIs REST sem sessão)
                .csrf(csrf -> csrf.disable())

                // Configura a autorização de requisições HTTP
                .authorizeHttpRequests(auth -> auth
                        // Permite acesso irrestrito aos caminhos do Swagger e API Docs
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/api/**" // Opcional: Se você quer que TODOS os seus endpoints REST sejam públicos
                        ).permitAll()

                        // Todas as outras requisições requerem autenticação (se houver outras URLs)
                        .anyRequest().authenticated()
                );

        // Se você não está usando autenticação via token (JWT) ou formulário,
        // mas apenas desabilitando o filtro, você pode terminar a configuração aqui.

        return http.build();
    }

    // Se você não planeja usar nenhum tipo de autenticação (sem usuários ou tokens)
    // e deseja tornar toda a sua aplicação pública, você pode simplificar ainda mais:
    /*
    @Bean
    public SecurityFilterChain securityFilterChainPublic(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
    */
}