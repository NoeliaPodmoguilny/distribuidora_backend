package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.PedidoDTO;
import com.distribuidora.distri.model.Pedido;
import com.distribuidora.distri.service.interfaz.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

    // traer lista de Pedidos
    @GetMapping("/get")
    public List<Pedido> getPedidos() {
        return pedidoService.getPedidos();
    }

    // traer un Pedido por id (id)
    @GetMapping("/get/{id}")
    public ResponseEntity<Pedido> getPedido(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.findPedido(id));
    }

    // crear nuevo Pedido
    @PostMapping("/save")
    public ResponseEntity<PedidoDTO> createPedido(@RequestBody PedidoDTO pedidoDTO) {
        pedidoService.savePedido(pedidoDTO);
        return ResponseEntity.ok(pedidoDTO);
    }

    // eliminar Pedido
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.ok("Pedido eliminado");
    }

    // editar Pedido
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updatePedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        pedidoService.updatePedido(id, pedidoDTO);
        return ResponseEntity.ok("Pedido actualizado!");

    }
}
