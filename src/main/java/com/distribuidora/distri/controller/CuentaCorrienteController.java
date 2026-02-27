package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.CuentaCorrienteDTO;
import com.distribuidora.distri.service.interfaz.ICuentaCorrienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentaCorriente")
public class CuentaCorrienteController {

    @Autowired
    private ICuentaCorrienteService cuentaCorrienteService;

    // traer lista de CuentaCorrientes
    @GetMapping("/get")
    public List<CuentaCorrienteDTO> getCuentaCorrientes() {
        return cuentaCorrienteService.getCuentaCorrientes();
    }

    // traer un CuentaCorriente por id (id)
    @GetMapping("/get/{id}")
    public CuentaCorrienteDTO getCuentaCorriente(@PathVariable Long id) {
        return cuentaCorrienteService.findCuentaCorriente(id);
    }

    @GetMapping("/getByIdCliente/{idCliente}")
    public CuentaCorrienteDTO findCuentaCorrienteByIdCliente(@PathVariable Long idCliente) {
        return cuentaCorrienteService.findCuentaCorrienteByIdCliente(idCliente);
    }

    // editar CuentaCorriente
    @PatchMapping("/update/{id}")
    public ResponseEntity<CuentaCorrienteDTO> updateCuentaCorriente(@PathVariable Long id,
            @Valid @RequestBody CuentaCorrienteDTO cuentaCorrienteDTO) {
        return ResponseEntity.ok(cuentaCorrienteService.updateCuentaCorriente(id, cuentaCorrienteDTO));
    }
}
