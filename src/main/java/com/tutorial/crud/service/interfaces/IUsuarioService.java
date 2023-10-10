package com.tutorial.crud.service.interfaces;


import com.tutorial.crud.dto.UsuarioDTO;
import com.tutorial.crud.security.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsuarioService {

//    public Usuario createUsuario(UsuarioDTO usuarioDTO);

    public List<Usuario> getAllUsuarios();

    public Usuario findById(Long id);

//    public Usuario login(UsuarioDTO usuarioDTO);

    public Usuario updateUsuario(UsuarioDTO usuarioDTO);

    public Usuario deleteUsuario(Long id);

}
