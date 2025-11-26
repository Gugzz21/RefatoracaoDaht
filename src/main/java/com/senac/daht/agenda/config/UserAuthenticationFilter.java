package com.senac.daht.agenda.config;

import com.senac.daht.agenda.repository.UsuarioRepository;
import com.senac.daht.agenda.entity.Usuario;
import com.senac.daht.agenda.service.JwtTokenService;
import com.senac.daht.agenda.service.UsuarioDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoveryToken(request);

        // LÓGICA CORRIGIDA:
        // 1. Se o token existir, tentamos validar.
        // 2. Se o token NÃO existir, NÃO lançamos erro. Apenas deixamos o request seguir.
        //    Se a rota exigir autenticação, o Spring Security vai retornar 403 Forbidden automaticamente depois.

        if (token != null) {
            try {
                String subject = jwtTokenService.getSubjectFromToken(token);

                // Usamos findByEmail e verificamos se existe para evitar erro de .get() em null
                Optional<Usuario> userOptional = userRepository.findByEmail(subject);

                if (userOptional.isPresent()) {
                    Usuario user = userOptional.get();
                    UsuarioDetailsImpl userDetails = new UsuarioDetailsImpl(user);

                    Authentication authentication =
                            new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                // Se o token for inválido, apenas ignoramos (não autenticamos) e deixamos o fluxo seguir.
                // O Spring Security barrará o acesso se necessário.
            }
        }

        // Continua o fluxo (vai para o Controller ou é barrado pelo SecurityConfig)
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}