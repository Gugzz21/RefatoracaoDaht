package com.senac.daht.agenda.dto;

import com.senac.daht.agenda.entity.RoleName;

public record CreateUserDto(

        String email,
        String password,
        RoleName role

) {}
