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

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoveryToken(request);

        if (token != null) {
            String subject = jwtTokenService.getSubjectFromToken(token);
            // Busca pelo email/subject
            Usuario usuario = userRepository.findByEmail(subject)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

            UsuarioDetailsImpl userDetails = new UsuarioDetailsImpl(usuario);

            // Cria o objeto de autenticação
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

            // Define no contexto de segurança
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}