package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.DetallePedidoDTO;
import com.distribuidora.distri.model.DetallePedido;
import com.distribuidora.distri.service.interfaz.IDetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detallePedido")
public class DetallePedidoController {

    @Autowired
    private IDetallePedidoService detallePedidoService;


    //    traer lista de DetallePedidos
    @GetMapping("/get")
    public List<DetallePedido> getDetallePedidos() {
        return detallePedidoService.getDetallePedidos();
    }

    //    traer un DetallePedido por id (id)
    @GetMapping("/get/{id}")
    public DetallePedido getDetallePedido(@PathVariable Long id) {
        return detallePedidoService.findDetallePedido(id);
    }

    // crear DetallePedido
    @PostMapping("/save")
    public ResponseEntity<DetallePedido> createDetallePedido(@RequestBody DetallePedidoDTO detallePedidoDTO) {
        return ResponseEntity.ok(detallePedidoService.saveDetallePedido(detallePedidoDTO));
    }

    //  eliminar DetallePedido
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDetallePedido(@PathVariable Long id) {
        detallePedidoService.deleteDetallePedido(id);
        return ResponseEntity.ok("Detalle del Pedido eliminado");
    }

    // editar DetallePedido
    @PatchMapping("/update/{id}")
    public ResponseEntity<DetallePedido> updateDetallePedido(@PathVariable Long id, @RequestBody DetallePedidoDTO detallePedidoDTO) {
        return ResponseEntity.ok(detallePedidoService.updateDetallePedido(id, detallePedidoDTO));

    }

    @GetMapping("/idPedido/{idPedido}")
    public ResponseEntity<List<DetallePedido>> buscarDetallesPorPedido(@PathVariable Long idPedido){
        return ResponseEntity.ok(detallePedidoService.buscarDetallesPorPedido(idPedido));
    }
}
