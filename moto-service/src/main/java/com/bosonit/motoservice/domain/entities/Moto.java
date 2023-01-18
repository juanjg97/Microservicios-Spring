package com.bosonit.motoservice.domain.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMoto;
    private int idUsuario;
    private String marca;
    private String modelo;
}
