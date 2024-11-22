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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Api REST clientes", description = "Test práctico")
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
 
    @Operation(summary = "Consulta de todos los clientes de la tabla Clientes de la base de datos.",
                description="SELECT * FROM Clientes;",
                tags = "Api REST clientes"
                )
    @GetMapping
    public ArrayList<Cliente> getClientes(){
        return this.clienteService.getClientes();
    }

    @Operation(summary = "Crea una nueva tupla en la tabla de Clientes de la base de datos.",
                description = "INSERT INTO Clientes('nombre','apPaterno','apMaterno','correo') VALUES(¿?,¿?,¿?,¿?);",
                tags = "Api REST clientes"
                )
    @PostMapping
    public Cliente saveCliente(@RequestBody Cliente cliente){
        return this.clienteService.saveCliente(cliente);
    }

    @Operation(summary = "Consulta un cliente específico de la tabla Clientes de la base de datos.",
                description = "SELECT * FROM Clientes WHERE id_cliente=¿?;",
                tags = "Api REST clientes"
                )
    @GetMapping(path = "/{id}")
    public Optional<Cliente> getClienteById(@PathVariable long id){
        return this.clienteService.getClienteById(id);
    }

    @Operation(summary = "Actualiza un cliente en la tabla Clientes de la base de datos.",
                description = "UPDATE Clientes SET (oldNombre=newNombre,OldApPaterno=NewApPaterno,OldApMaterno=NewApMaterno,OldCorreo=NewCorreo) WHERE id_cliente=¿?;",
                tags = "Api REST clientes"
                )
    @PutMapping(path = "/{id}")
    public Cliente updateClienteById(@RequestBody Cliente clienteModificado, @PathVariable("id") Long id){
        return this.clienteService.updateClienteById(clienteModificado, id);
    }

    @Operation(summary = "Elimina un cliente en la tabla clientes de la base de datos.",
                description = "DELETE FROM Clientes WHERE id_cliente=¿?;",
                tags = "Api REST clientes"
                )
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
