package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.request.ActivateEmpleadoDTO;
import com.distribuidora.distri.dto.request.EmpleadoRequestDTO;
import com.distribuidora.distri.dto.response.EmpleadoResponseDTO;
import com.distribuidora.distri.service.interfaz.IEmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    // traer lista de Empleados
    @GetMapping("/get")
    public List<EmpleadoResponseDTO> getEmpleados() {
        return empleadoService.getEmpleados();
    }

    // traer un Empleado por id (id)
    @GetMapping("/get/{id}")
    public EmpleadoResponseDTO getEmpleado(@PathVariable Long id) {
        return empleadoService.findEmpleado(id);
    }

    @GetMapping("/getBynombreEmpleado/{nombreEmpleado}")
    public EmpleadoResponseDTO findEmpleadoByNombre(@PathVariable String nombreEmpleado) {
        return empleadoService.findEmpleadoByNombre(nombreEmpleado);
    }

    // crear Empleado
    @PostMapping("/save")
    public ResponseEntity<String> createEmpleado(@Valid @RequestBody EmpleadoRequestDTO empleadoDTO) {
        ResponseEntity.ok(empleadoService.saveEmpleado(empleadoDTO));
        return ResponseEntity.ok("Empleado registrado correctamente");
    }

    // eliminar Empleado
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmpleado(@PathVariable Long id) {
        empleadoService.deleteEmpleado(id);
        return ResponseEntity.ok("Empleado eliminado");
    }

    // editar Empleado
    @PatchMapping("/update/{id}")
    public ResponseEntity<EmpleadoResponseDTO> updateEmpleado(@PathVariable Long id,
            @Valid @RequestBody EmpleadoRequestDTO empleadoDTO) {
        return ResponseEntity.ok(empleadoService.updateEmpleado(id, empleadoDTO));
    }

    // activar desactivar empleado
    @PatchMapping("activo/{idSeleccionado}")
    public ResponseEntity<String> isActivo(@PathVariable Long idSeleccionado,
            @RequestBody ActivateEmpleadoDTO activateEmpleado) {
        empleadoService.isActivo(idSeleccionado, activateEmpleado);
        return ResponseEntity.ok("Cambio de estado exitosamente");
    }
}
