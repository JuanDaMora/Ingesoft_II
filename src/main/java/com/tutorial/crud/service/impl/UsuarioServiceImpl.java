package com.tutorial.crud.service.impl;

import com.tutorial.crud.dto.UsuarioDTO;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.security.model.Rol;
import com.tutorial.crud.security.model.Usuario;
import com.tutorial.crud.security.repository.UsuarioRepository;
import com.tutorial.crud.security.service.RolService;
import com.tutorial.crud.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolService rolService;
    UsuarioRepository usuarioRepository;
    LocalDateTime today = LocalDateTime.now();

//    @Override
//    public Usuario createUsuario(UsuarioDTO usuarioDTO) {
//        Usuario usarioToCreated = UsuarioMappers.INSTANCE.usuarioDTOToUsuario(usuarioDTO);
//        usarioToCreated.setFechaCreacion(today);
//        return usuarioRepository.save(usarioToCreated);
//    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

//    @Override
//    public Usuario login(UsuarioDTO usuarioDTO) {
//        String correoElectronico = usuarioDTO.getCorreoElectronico();
//        String contraseña = usuarioDTO.getContrasena();
//        // Buscar el usuario por el correo electrónico
//        Optional<Usuario> optionalUsuario = usuarioRepository.findByCorreoElectronico(correoElectronico);
//        if (optionalUsuario.isPresent()) {
//            Usuario usuario = optionalUsuario.get();
//            // Verificar la contraseña
//            if (usuario.getContraseña().equals(contraseña)) {
//                // La contraseña es correcta, devolver el DTO del usuario
//                return usuario;
//            }
//        }
//        // El usuario no existe o la contraseña es incorrecta
//        return null;
//    }

    public Usuario updateUsuario(UsuarioDTO usuarioDTO) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioDTO.getIdUsuario());
        if (optionalUsuario.isPresent()) {
            Usuario usuario =
                    new Usuario(usuarioDTO.getPrimerNombre(),usuarioDTO.getSegundoNombre(), usuarioDTO.getPrimerApellido(), usuarioDTO.getSegundoApellido(), usuarioDTO.getDocumentoIdentidad(), usuarioDTO.getCorreoElectronico(),
                            passwordEncoder.encode(usuarioDTO.getContrasena()));
            Set<Rol> roles = new HashSet<>();
            if(usuarioDTO.getRoles().contains("administrador"))
                roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMINISTRADOR).get());
            else if (usuarioDTO.getRoles().contains("medico")) {
                roles.add(rolService.getByRolNombre(RolNombre.ROLE_MEDICO).get());
            }else {
                roles.add(rolService.getByRolNombre(RolNombre.ROLE_PACIENTE).get());
            }
            usuario.setRoles(roles);
            usuario.setId(usuarioDTO.getIdUsuario());
            // Guardar los cambios en la base de datos
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }

    @Override
    public Usuario deleteUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(null);
        if(usuario != null){
            usuarioRepository.delete(usuario);
            return usuario;
        }
        return null;
    }

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

}
