package com.arturoaragon.testpractico.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
 
    /*
     * GET all
     */
    @Operation(summary = "Consulta de todos los clientes de la tabla Clientes de la base de datos.",
                description="SELECT * FROM Clientes;",
                tags = "Api REST clientes"
                )
    @GetMapping
    public @ResponseBody ResponseEntity getClientes(){
        ArrayList<Cliente> clientes = this.clienteService.getClientes();
        if(clientes.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron clientes en la base.");
        } else{
            return new ResponseEntity<>(clientes,HttpStatus.OK);
        }
    }

    /*
     * POST
     */
    @Operation(summary = "Crea una nueva tupla en la tabla de Clientes de la base de datos.",
                description = "INSERT INTO Clientes('nombre','apPaterno','apMaterno','correo') VALUES(¿?,¿?,¿?,¿?);",
                tags = "Api REST clientes"
                )
    @PostMapping
    public @ResponseBody ResponseEntity saveCliente(@RequestBody Cliente cliente){
        try{
            return new ResponseEntity<>(this.clienteService.saveCliente(cliente), HttpStatus.CREATED);
        }catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo crear el recurso.");
        }
    }

    /*
     * GET one
     */
    @Operation(summary = "Consulta un cliente específico de la tabla Clientes de la base de datos.",
                description = "SELECT * FROM Clientes WHERE id_cliente=¿?;",
                tags = "Api REST clientes"
                )
    @GetMapping(path = "/{id}")
    public @ResponseBody ResponseEntity getClienteById(@PathVariable long id) {
        Optional<Cliente> cliente = this.clienteService.getClienteById(id);
        if(cliente.isPresent()){
            return new ResponseEntity(cliente, HttpStatus.OK);
        } else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("El cliente " + id + " no fué encontrado.");
        }
    }

    /*
     * PUT
     */
    @Operation(summary = "Actualiza un cliente en la tabla Clientes de la base de datos.",
                description = "UPDATE Clientes SET (oldNombre=newNombre,OldApPaterno=NewApPaterno,OldApMaterno=NewApMaterno,OldCorreo=NewCorreo) WHERE id_cliente=¿?;",
                tags = "Api REST clientes"
                )
    @PutMapping(path = "/{id}")
    public @ResponseBody ResponseEntity updateClienteById(@RequestBody Cliente clienteModificado, @PathVariable("id") Long id){
        try{
            return new ResponseEntity<Cliente>(this.clienteService.updateClienteById(clienteModificado, id),HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("El cliente " + id + " no fué encontrado.");
        }
    }

    /*
     * DELETE
     */
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
