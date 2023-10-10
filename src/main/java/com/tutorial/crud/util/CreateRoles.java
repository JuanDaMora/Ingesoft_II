package com.tutorial.crud.util;

import com.tutorial.crud.security.model.Rol;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * MUY IMPORTANTE: ESTA CLASE SÓLO SE EJECUTARÁ UNA VEZ PARA CREAR LOS ROLES.
 * UNA VEZ CREADOS SE DEBERÁ ELIMINAR O BIEN COMENTAR EL CÓDIGO
 *
 */

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMINISTRADOR);
        Rol rolUser = new Rol(RolNombre.ROLE_PACIENTE);
        Rol rolMedico = new Rol(RolNombre.ROLE_MEDICO);
        rolService.save(rolAdmin);
        rolService.save(rolUser);
        rolService.save(rolMedico);


    }
}
