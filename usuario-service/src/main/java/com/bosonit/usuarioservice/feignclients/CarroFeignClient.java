package com.bosonit.usuarioservice.feignclients;

import com.bosonit.usuarioservice.models.Carro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "carro-service",url="http://localhost:8002")
@RequestMapping("/carro")
public interface CarroFeignClient {
    @PostMapping
    public Carro saveCarroFromFeign(@RequestBody Carro carro);

    @GetMapping("/usuario/{idUsuario}")
    public List<Carro> getCarrosFromFeign(@PathVariable("idUsuario") int idUsuario);
}
