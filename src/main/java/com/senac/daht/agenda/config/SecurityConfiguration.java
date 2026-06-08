package com.senac.daht.agenda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
        @Autowired
        private UserAuthenticationFilter userAuthenticationFilter;

        public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
                        // Debug
                        "/api/usuario/debug",

                        // Usuário
                        "/api/usuario/criar",
                        "/api/usuario/login",

                        // Página HTML
                        "/",
                        "/index.html",
                        "/install",
                        "/images/**",

                        "/h2-console/**", // Adicionado wildcard para o h2

                        // 🔓 Swagger/OpenAPI UI
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
        };

        // Endpoints que requerem autenticação para serem acessados
        public static final String[] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {

                        // Usuário
                        "/api/usuario/me",
                        "/api/usuario/listar",
                        "/api/usuario/listarPorId/**",
                        "/api/usuario/atualizar/**",
                        "/api/usuario/deletar/**",

                        // Personagem
                        "/api/personagem/listar",
                        "/api/personagem/listarPorId/**",
                        "/api/personagem/atualizar/**",
                        "/api/personagem/deletar/**",
                        "/api/personagem/criar",

                        // Missão
                        "/api/missao/listar",
                        "/api/missao/listarPorId/**",
                        "/api/missao/atualizar/**",
                        "/api/missao/deletar/**",
                        "/api/missao/criar",

                        // Ganho
                        "/api/ganho/listar",
                        "/api/ganho/listarPorId/**",
                        "/api/ganho/atualizar/**",
                        "/api/ganho/deletar/**",
                        "/api/ganho/criar",

                        // Prêmio
                        "/api/premio/listar",
                        "/api/premio/listarPorId/**",
                        "/api/premio/atualizar/**",
                        "/api/premio/deletar/**",
                        "/api/premio/criar",

                        // Tabela Prêmio
                        "/api/tabelapremio/listar",
                        "/api/tabelapremio/listarPorId/**",
                        "/api/tabelapremio/deletar/**",
                        "/api/tabelapremio/criar"
        };

        // Endpoints que só podem ser acessador por usuários com permissão de cliente
        public static final String[] ENDPOINTS_CUSTOMER = {
                        "/usuario/test/customer"
        };

        // Endpoints que só podem ser acessador por usuários com permissão de
        // administrador
        public static final String[] ENDPOINTS_ADMIN = {
                        "/usuario/test/administrator"
        };

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable) // Nova sintaxe para desabilitar CSRF
                                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // <--- ESSA LINHA
                                                                                                   // FALTAVA: Ativa o
                                                                                                   // CORS usando a
                                                                                                   // configuração
                                                                                                   // abaixo
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Libera
                                                                                                        // requisições
                                                                                                        // OPTIONS
                                                                                                        // (Preflight)
                                                .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR")
                                                .requestMatchers(ENDPOINTS_CUSTOMER).hasRole("CUSTOMER")
                                                // .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                                                // // Opcional se usar anyRequest().authenticated()
                                                .anyRequest().authenticated() // Qualquer outra rota exige autenticação
                                )
                                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        // --- CONFIGURAÇÃO GLOBAL DE CORS ---
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                // Fix #6: allowedOrigins("*") é incompatível com credenciais.
                // allowedOriginPatterns("*") funciona corretamente em dev.
                // ATENÇÃO: Substituir "*" pelos domínios específicos antes do deploy em produção.
                // Exemplo: configuration.setAllowedOriginPatterns(List.of("https://app.seudominio.com.br"));
                configuration.setAllowedOriginPatterns(List.of("*"));
                configuration.setAllowedMethods(
                                Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT"));
                configuration.setAllowedHeaders(List.of("*"));

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }
        // -----------------------------------


        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}