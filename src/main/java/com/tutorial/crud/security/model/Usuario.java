package com.tutorial.crud.security.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@Table(name = "USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String primerNombre;
    @NotNull
    @Column(unique = true)
    private String nombreUsuario;
    private String segundoNombre;
    @NotBlank
    private String primerApellido;
    private String segundoApellido;
    @NotNull
    @Column(unique = true)
    private Integer documentoIdentidad;
    private LocalDateTime fechaCreacion;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(@NotNull String primerNombre,String segundoNombre,@NotBlank String primerApellido,String segundoApellido,@NotBlank Integer documentoIdentidad, @NotNull String email, @NotNull String password) {
        this.primerNombre = primerNombre;
        this.segundoNombre=segundoNombre;
        this.primerApellido=primerApellido;
        this.segundoApellido=segundoApellido;
        this.documentoIdentidad=documentoIdentidad;
        this.nombreUsuario = email;
        this.email = email;
        this.password = password;
    }

}
