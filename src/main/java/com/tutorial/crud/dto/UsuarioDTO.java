package com.tutorial.crud.dto;

import com.tutorial.crud.security.model.Rol;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter@Setter
public class UsuarioDTO {

    private Long idUsuario;
    private String primerNombre;
    private String contrasena;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Integer documentoIdentidad;
    private LocalDateTime fechaCreacion;
    @Email
    private String correoElectronico;
    private Set<String> roles = new HashSet<>();
}
