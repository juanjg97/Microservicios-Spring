package com.bosonit.motoservice.controllers;

import com.bosonit.motoservice.MotoServiceApplication;
import com.bosonit.motoservice.application.services.MotoServiceImp;
import com.bosonit.motoservice.domain.entities.Moto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {
    @Autowired
    private MotoServiceImp motoService;

    @PostMapping
    public ResponseEntity<Moto> saveMoto(@RequestBody Moto moto){
        Moto nuevaMoto = motoService.saveMoto(moto);
        return ResponseEntity.ok(nuevaMoto);
    }


    @GetMapping
    public ResponseEntity<List<Moto>> getAllCarros(){
        List<Moto> motosList = motoService.getAllMotos();
        if(motosList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motosList);
    }


    @GetMapping("/{idMoto}")
    public ResponseEntity<Moto> getCarroById(@PathVariable("idMoto") int idMoto){
        Moto moto = motoService.getMotoById(idMoto);
        if(moto==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moto);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Moto>> getCarrosByUserId(@PathVariable("idUsuario") int idUsuario){
        List<Moto> motosList = motoService.getMotosByUserId(idUsuario);
        if(motosList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motosList);
    }
}
