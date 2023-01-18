package carroservice.carroservice.application.services;

import carroservice.carroservice.domain.entities.Carro;
import carroservice.carroservice.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroServiceImp {
    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> getAllCarros(){
        return carroRepository.findAll();
    }

    public Carro getCarroById(int id_carro){
        Carro carro = carroRepository.findById(id_carro).orElseThrow();
        return carro;
    }

    public Carro saveCarro(Carro carro){
        Carro nuevoCarro = carroRepository.save(carro);
        return  carro;
    }

    public List<Carro> getCarrosByUserId(int idUsuario){
        return carroRepository.findByIdUsuario(idUsuario);
    }
}
