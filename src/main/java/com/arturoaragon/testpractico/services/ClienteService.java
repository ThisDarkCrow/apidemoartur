package com.arturoaragon.testpractico.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arturoaragon.testpractico.models.Cliente;
import com.arturoaragon.testpractico.repositories.IClienteRepository;

@Service
public class ClienteService {
    @Autowired
    IClienteRepository clienteRepository;

    public ArrayList<Cliente> getClientes(){
        return (ArrayList<Cliente>) clienteRepository.findAll();
    }

    public Cliente saveCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> getClienteById(Long Id) {
        return clienteRepository.findById(Id);
    }

    public Cliente updateClienteById(Cliente clienteModificado, Long id){
        Cliente clienteGuardado = clienteRepository.findById(id).get();
        
        clienteGuardado.setNombre(clienteModificado.getNombre());
        clienteGuardado.setApPaterno(clienteModificado.getApPaterno());
        clienteGuardado.setApMaterno(clienteModificado.getApMaterno());
        clienteGuardado.setCorreo(clienteModificado.getCorreo());

        return clienteGuardado;
    }

    public boolean deleteClienteById(Long id){
        try{
            clienteRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    
}
