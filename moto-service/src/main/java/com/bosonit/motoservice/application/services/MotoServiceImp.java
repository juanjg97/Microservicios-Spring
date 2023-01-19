package com.bosonit.motoservice.application.services;

import com.bosonit.motoservice.domain.entities.Moto;
import com.bosonit.motoservice.repositories.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoServiceImp {
    @Autowired
    private MotoRepository motoRepository;

    public List<Moto> getAllMotos(){
        return motoRepository.findAll();
    }

    public Moto getMotoById(int idMoto){
        Moto moto = motoRepository.findById(idMoto).orElse(null);
        return moto;
    }

    public Moto saveMoto(Moto moto){
        Moto nuevaMoto = motoRepository.save(moto);
        return  moto;
    }

    public List<Moto> getMotosByUserId(int idUsuario){

        return motoRepository.findByIdUsuario(idUsuario);
    }
}
