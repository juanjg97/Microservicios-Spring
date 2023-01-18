package com.bosonit.usuarioservice.application.services;

import com.bosonit.usuarioservice.domain.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bosonit.usuarioservice.repositories.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioServiceImp {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id_usuario){
        Usuario usuario = usuarioRepository.findById(id_usuario).orElseThrow();
        return usuario;
    }

    public Usuario saveUsuario(Usuario usuario){
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return  usuario;
    }

}
