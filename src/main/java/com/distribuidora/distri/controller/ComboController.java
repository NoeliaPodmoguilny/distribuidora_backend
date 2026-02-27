package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.request.ComboRequestDTO;
import com.distribuidora.distri.dto.response.ComboDTO;
import com.distribuidora.distri.service.interfaz.IComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/combo")
public class ComboController {

    @Autowired
    private IComboService comboService;

    // traer lista de Combos
    @GetMapping("/get")
    public ResponseEntity<List<ComboDTO>> getCombos() {
        return ResponseEntity.ok(comboService.getCombos());
    }

    // traer un Combo por id (id)
    @GetMapping("/get/{id}")
    public ResponseEntity<ComboDTO> getCombo(@PathVariable Long id) {
        return ResponseEntity.ok(comboService.findCombo(id));
    }

    // crear Combo
    @PostMapping("/save")
    public ResponseEntity<String> createCombo(@RequestBody ComboRequestDTO comboDTO) {
        comboService.saveCombo(comboDTO);
        return ResponseEntity.ok("Combo creado correctamente!");
    }

    // eliminar Combo
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCombo(@PathVariable Long id) {
        comboService.deleteCombo(id);
        return ResponseEntity.ok("Combo eliminado");
    }

    // editar Combo
    @PatchMapping("/update/{id}")
    public ResponseEntity<ComboDTO> updateCombo(@PathVariable Long id, @RequestBody ComboRequestDTO comboDTO) {
        return ResponseEntity.ok(comboService.updateCombo(id, comboDTO));
    }

}
