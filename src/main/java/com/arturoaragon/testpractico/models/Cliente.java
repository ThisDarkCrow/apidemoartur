package com.arturoaragon.testpractico.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Clientes")
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long idCliente;

    @Getter
    @Setter
    private String nombre;

    @Getter
    @Setter
    private String apPaterno;

    @Getter
    @Setter
    private String apMaterno;

    @Getter
    @Setter
    private String correo;
    
}
