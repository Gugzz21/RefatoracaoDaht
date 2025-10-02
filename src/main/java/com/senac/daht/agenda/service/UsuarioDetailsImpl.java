package com.senac.daht.agenda.service;

import com.senac.daht.agenda.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UsuarioDetailsImpl implements UserDetails {

    private final Usuario usuario;

    public UsuarioDetailsImpl(Usuario usuario){
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNome().name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    public Integer getIdUsuario(){
        return usuario.getId();
    }

    public String getNomeUsuario(){
        return usuario.getNome();
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return usuario.getStatus() != null && usuario.getStatus() >= 0; }
}