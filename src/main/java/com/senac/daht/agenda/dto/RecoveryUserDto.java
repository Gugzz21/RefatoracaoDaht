package com.senac.daht.agenda.dto;

import com.senac.daht.agenda.entity.Role;

import java.util.List;

public record RecoveryUserDto(

        Long id,
        String email,
        List<Role> roles

) {
}
