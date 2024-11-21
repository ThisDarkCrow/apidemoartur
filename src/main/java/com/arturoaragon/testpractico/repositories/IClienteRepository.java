package com.arturoaragon.testpractico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arturoaragon.testpractico.models.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long>{}