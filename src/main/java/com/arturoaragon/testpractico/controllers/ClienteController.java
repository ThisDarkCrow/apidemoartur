package com.arturoaragon.testpractico.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arturoaragon.testpractico.models.Cliente;
import com.arturoaragon.testpractico.services.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
 
    @GetMapping
    public ArrayList<Cliente> getClientes(){
        return this.clienteService.getClientes();
    }

    @PostMapping
    public Cliente saveEmpleado(@RequestBody Cliente cliente){
        return this.clienteService.saveCliente(cliente);
    }

    @GetMapping(path = "/{id}")
    public Optional<Cliente> getClienteById(@PathVariable long id){
        return this.clienteService.getClienteById(id);
    }

    @PutMapping(path = "/{id}")
    public Cliente updateEmpleadoById(@RequestBody Cliente clienteModificado, @PathVariable("id") Long id){
        return this.clienteService.updateClienteById(clienteModificado, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteClienteById(@PathVariable("id") Long id){
        boolean isEliminado= this.clienteService.deleteClienteById(id);

        if(isEliminado){
            return "Cliente con id "+id+", ha sido eliminado.";
        }else{
            return "Problema con la operación de eliminación.";
        }
    }
    
}
