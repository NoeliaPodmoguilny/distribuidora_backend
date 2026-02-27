package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.PermisoDTO;
import com.distribuidora.distri.model.Permiso;
import com.distribuidora.distri.service.interfaz.IPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permiso")
public class PermisoController {

    @Autowired
    private IPermisoService permisoService;


    //    traer lista de Permisos
    @GetMapping("/get")
    public List<Permiso> getPermisos() {
        return permisoService.getPermisos();
    }

    //    traer un Permiso por id (id)
    @GetMapping("/get/{id}")
    public Permiso getPermiso(@PathVariable Long id) {
        return permisoService.findPermiso(id);
    }

    // crear Permiso
    @PostMapping("/save")
    public ResponseEntity<Permiso> createPermiso(@RequestBody PermisoDTO permisoDTO) {
        return ResponseEntity.ok(permisoService.savePermiso(permisoDTO));
    }

    //  eliminar Permiso
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePermiso(@PathVariable Long id) {
        permisoService.deletePermiso(id);
        return ResponseEntity.ok("Permiso eliminado");
    }

    // editar Permiso
    @PatchMapping("/update/{id}")
    public ResponseEntity<Permiso> updatePermiso(@PathVariable Long id, @RequestBody PermisoDTO permisoDTO) {
        return ResponseEntity.ok(permisoService.updatePermiso(id, permisoDTO));

    }
}
