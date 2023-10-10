package com.tutorial.crud.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
public class NuevoUsuario {
    @NotBlank
    private String primerNombre;
    private String segundoNombre;
    @NotBlank
    private String primerApellido;
    private String segundoApellido;
    @NotNull
    private Integer documentoIdentidad;

    @Email
    private String correoElectronico;
    @NotBlank
    private String contrasena;
    private Set<String> roles = new HashSet<>();

}
