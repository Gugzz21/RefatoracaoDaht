package com.senac.daht.agenda.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todas as rotas
                .allowedOrigins(
                        "http://localhost:8080", // Para desenvolvimento no navegador/web
                        "http://academico3.rj.senac.br" // Origem do servidor (pode ser redundante, mas seguro)
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*"); // Permite cabeçalhos customizados, como o 'Authorization'
    }
}