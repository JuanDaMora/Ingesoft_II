package com.tutorial.crud.mapper;

import com.tutorial.crud.dto.UsuarioDTO;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.security.model.Rol;
import com.tutorial.crud.security.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UsuarioMapper {
    UsuarioMapper INSTANCE= Mappers.getMapper(UsuarioMapper.class);
    @Mapping(target="contrasena",source="password")
    @Mapping(target="correoElectronico",source = "email")
    @Mapping(target = "idUsuario",source="id")
        UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);
    @Mapping(target="password",source="contrasena")
    @Mapping(target="email",source = "correoElectronico")
    @Mapping(target = "id",source="idUsuario")
    Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO);

    default Set<String> rolesToStrings(Set<Rol> roles) {
        return roles.stream()
                .map(rol -> rol.getRolNombre().name()) // Obtén el nombre del rol
                .collect(Collectors.toSet());
    }

    default Set<Rol> stringsToRoles(Set<String> roleNames) {
        return roleNames.stream()
                .map(RolNombre::valueOf) // Convierte el nombre del rol a un RolNombre
                .map(Rol::new) // Crea un objeto Rol a partir del RolNombre
                .collect(Collectors.toSet());
    }
}
