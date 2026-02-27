package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.ClienteDTO;
import com.distribuidora.distri.model.Cliente;
import com.distribuidora.distri.service.interfaz.IClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;


    //    traer lista de Clientes
    @GetMapping("/get")
    public List<Cliente> getClientes() {
        return clienteService.getClientes();
    }

    //    traer un Cliente por id (id)
    @GetMapping("/get/{id}")
    public Cliente getCliente(@PathVariable Long id) {
        return clienteService.findCliente(id);
    }

    @GetMapping("/getBynombreCliente/{nombreCliente}")
    public Cliente findClienteBynombreCliente(@PathVariable String nombreCliente) {
        return clienteService.findClienteBynombreCliente(nombreCliente);
    }

    // crear Cliente
    @PostMapping("/save")
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.saveCliente(clienteDTO));
    }

    //  eliminar Cliente
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.ok("Cliente eliminado");
    }

    // editar Cliente
    @PatchMapping("/update/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.updateCliente(id, clienteDTO));
    }
}
