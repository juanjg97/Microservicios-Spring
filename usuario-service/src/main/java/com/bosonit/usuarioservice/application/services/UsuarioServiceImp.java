package com.bosonit.usuarioservice.application.services;

import com.bosonit.usuarioservice.feignclients.CarroFeignClient;
import com.bosonit.usuarioservice.feignclients.MotoFeignClient;
import com.bosonit.usuarioservice.models.Carro;
import com.bosonit.usuarioservice.domain.entities.Usuario;
import com.bosonit.usuarioservice.models.Moto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bosonit.usuarioservice.repositories.UsuarioRepository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioServiceImp {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CarroFeignClient carroFeignClient;
    @Autowired
    private MotoFeignClient motoFeignClient;

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id_usuario){
        Usuario usuario = usuarioRepository.findById(id_usuario).orElse(null);
        return usuario;
    }

    public Usuario saveUsuario(Usuario usuario){
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return  usuario;
    }
    /*---------------------------------------------------------------------*/
    //Vamos a usar desde aquí los endpoints creados en los controladores de Carro y Moto

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
    //Feign Client
    public Carro saveCarroWithFeign (Carro carro){
        //Vamos a usar desde aquí los endpoints creados en los controladores de Carro y Moto
        carro.setIdUsuario(carro.getIdUsuario());
        Carro nuevoCarro = carroFeignClient.saveCarroFromFeign(carro);
        return nuevoCarro;
    }

    public Moto saveMotoWithFeign (Moto moto){
        moto.setIdUsuario(moto.getIdUsuario());
        Moto nuevaMoto = motoFeignClient.saveMotoFromFeign(moto);
        return nuevaMoto;
    }

    public List<Moto> getMotosWithFeign(int idUsuario){
        List<Moto> motosList = motoFeignClient.getMotosFromFeign(idUsuario);
        return motosList;
    }

    public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
        Map<String,Object> resultado = new HashMap<>();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if(usuario == null) {
            resultado.put("Mensaje", "El usuario no existe");
            return resultado;
        }

        resultado.put("Usuario",usuario);
        List<Carro> carros = carroFeignClient.getCarrosFromFeign(usuarioId);
        if(carros.isEmpty()) {
            resultado.put("Carros", "El usuario no tiene carros");
        }
        else {
            resultado.put("Carros", carros);
        }

        List<Moto> motos = motoFeignClient.getMotosFromFeign(usuarioId);
        if(motos.isEmpty()) {
            resultado.put("Motos", "El usuario no tiene motos");
        }
        else {
            resultado.put("Motos", motos);
        }
        return resultado;
    }


    /*---------------------------------------------------------------------*/

}
