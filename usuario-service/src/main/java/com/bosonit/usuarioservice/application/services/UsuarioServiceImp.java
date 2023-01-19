package com.bosonit.usuarioservice.application.services;

import com.bosonit.usuarioservice.models.Carro;
import com.bosonit.usuarioservice.domain.entities.Usuario;
import com.bosonit.usuarioservice.models.Moto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bosonit.usuarioservice.repositories.UsuarioRepository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UsuarioServiceImp {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RestTemplate restTemplate;

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
    /*---------------------------------------------------------------------*/
    //RestTemplate
    public List<Carro> getAllCarros(int idUsuario){
        String carrosServicePath = "http://localhost:8002/carro/usuario/";
        List<Carro> carrosList = restTemplate.getForObject(carrosServicePath+idUsuario,List.class);
        return carrosList;
    }

    public List<Moto> getAllMotos(int idUsuario){
        String motosServicePath = "http://localhost:8003/moto/usuario/";
        List<Moto> motosList = restTemplate.getForObject(motosServicePath+idUsuario,List.class);
        return motosList;
    }


    /*---------------------------------------------------------------------*/

}
