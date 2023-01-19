package com.bosonit.usuarioservice.feignclients;

import com.bosonit.usuarioservice.models.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "moto-service",url="http://localhost:8003")
@RequestMapping("/moto")
public interface MotoFeignClient {
    @PostMapping
    public Moto saveMotoFromFeign(@RequestBody Moto moto);

    @GetMapping("/usuario/{idUsuario}")
    public List<Moto> getMotosFromFeign(@PathVariable int idUsuario);
}
