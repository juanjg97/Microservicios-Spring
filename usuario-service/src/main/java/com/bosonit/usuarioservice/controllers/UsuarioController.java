package com.bosonit.usuarioservice.controllers;

import com.bosonit.usuarioservice.application.services.UsuarioServiceImp;
import com.bosonit.usuarioservice.domain.entities.Usuario;
import com.bosonit.usuarioservice.models.Carro;
import com.bosonit.usuarioservice.models.Moto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bosonit.usuarioservice.repositories.UsuarioRepository;

import java.util.List;
import java.util.Map;

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
    /*-----------------------------------------------------------------------*/
    //RestTemplate
    @GetMapping("/g-carros-rt/{idUsuario}")
    public ResponseEntity<List<Carro>> getCarros(@PathVariable("idUsuario") int idUsuario){
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        if(usuario==null){
            return  ResponseEntity.notFound().build();
        }
        List<Carro> carrosList = usuarioService.getAllCarros(idUsuario);
        return ResponseEntity.ok(carrosList);
    }

    @GetMapping("/g-motos-rt/{idUsuario}")
    public ResponseEntity<List<Moto>> getMotos(@PathVariable("idUsuario") int idUsuario){
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        if(usuario==null){
            return  ResponseEntity.notFound().build();
        }
        List<Moto> motosList = usuarioService.getAllMotos(idUsuario);
        return ResponseEntity.ok(motosList);
    }

    /*-----------------------------------------------------------------------*/
    //Feign Client
    @PostMapping("/s-carro-cf")
    public ResponseEntity<Carro> saveCarroWithFeig(@RequestBody Carro carro){

        Carro nuevoCarro = usuarioService.saveCarroWithFeign(carro);
        return ResponseEntity.ok(nuevoCarro);
    }
    @PostMapping("/s-moto-cf")
    public ResponseEntity<Moto> saveMotoWithFeign(@RequestBody Moto moto){

        Moto nuevaMoto = usuarioService.saveMotoWithFeign(moto);
        return ResponseEntity.ok(nuevaMoto);
    }

    @GetMapping("/g-moto-cf/{idUsuario}")
    public ResponseEntity<List<Moto>> getMotosWithFeign(@PathVariable("idUsuario") int idUsuario){
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        if(usuario==null){
            return  ResponseEntity.notFound().build();
        }
        List<Moto> motosList = usuarioService.getAllMotos(idUsuario);
        return ResponseEntity.ok(motosList);
    }

    @GetMapping("/g-all-cf/{idUsuario}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("idUsuario") int idUsuario){
        Map<String,Object> allVehiclesList = usuarioService.getUsuarioAndVehiculos(idUsuario);
        return ResponseEntity.ok(allVehiclesList);
    }


}
