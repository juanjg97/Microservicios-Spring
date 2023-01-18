package com.bosonit.usuarioservice.controllers;

import com.bosonit.usuarioservice.application.services.UsuarioServiceImp;
import com.bosonit.usuarioservice.domain.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bosonit.usuarioservice.repositories.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImp usuarioService;


    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }


    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> usuariosList = usuarioService.getAllUsuarios();
        if(usuariosList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuariosList);
    }


    @GetMapping("/{id_usuario}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id_usuario") int id_usuario){
        Usuario usuario = usuarioService.getUsuarioById(id_usuario);
        if(usuario==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
}
