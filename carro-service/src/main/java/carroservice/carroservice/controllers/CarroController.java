package carroservice.carroservice.controllers;

import carroservice.carroservice.application.services.CarroServiceImp;
import carroservice.carroservice.domain.entities.Carro;
import carroservice.carroservice.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {
    @Autowired
    private CarroServiceImp carroService;

    @PostMapping
    public ResponseEntity<Carro> saveCarro(@RequestBody Carro carro){
        Carro nuevoCarro = carroService.saveCarro(carro);
        return ResponseEntity.ok(nuevoCarro);
    }


    @GetMapping
    public ResponseEntity<List<Carro>> getAllCarros(){
        List<Carro> carrosList = carroService.getAllCarros();
        if(carrosList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carrosList);
    }


    @GetMapping("/{idCarro}")
    public ResponseEntity<Carro> getCarroById(@PathVariable("idCarro") int idCarro){
        Carro carro = carroService.getCarroById(idCarro);
        if(carro==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carro);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Carro>> getCarrosByUserId(@PathVariable("idUsuario") int idUsuario){
        List<Carro> carrosList = carroService.getCarrosByUserId(idUsuario);
        if(carrosList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carrosList);
    }
}
