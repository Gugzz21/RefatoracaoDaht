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

    // Caminhos Públicos (Liberados)
    public static final String [] ENDPOINTS_PUBLIC = {
            "/api/usuario/criar",    // Criação de usuário (POST)
            "/api/usuario/login",    // Login (para gerar o token)

            // Swagger/OpenAPI UI
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desabilita CSRF
                .csrf(csrf -> csrf.disable())

                // 2. Define a política de sessão como STATELESS (padrão JWT)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Define as Regras de Autorização (CRÍTICO)
                .authorizeHttpRequests(auth -> auth

                        // PRIMEIRA REGRA: Libera os endpoints públicos para acesso anônimo
                        .requestMatchers(ENDPOINTS_PUBLIC).permitAll()

                        // SEGUNDA REGRA: Todo o resto (incluindo /api/listar, /api/deletar, etc.)
                        // deve passar pelo processo de autenticação (filtro JWT)
                        .anyRequest().authenticated()
                )

                // 4. Adiciona o filtro JWT customizado ANTES do filtro de autenticação padrão
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

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