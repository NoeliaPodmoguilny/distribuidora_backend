package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.MetodoDePagoDTO;
import com.distribuidora.distri.model.MetodoDePago;
import com.distribuidora.distri.service.interfaz.IMetodoDePagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metodoDePago")
public class MetodoDePagoController {
    
        @Autowired
        private IMetodoDePagoService metodoDePagoService;


        //    traer lista de MetodoDePagos
        @GetMapping("/get")
        public List<MetodoDePago> getMetodoDePagos() {
            return metodoDePagoService.getMetodoDePagos();
        }

        //    traer un MetodoDePago por id (id)
        @GetMapping("/get/{id}")
        public MetodoDePago getMetodoDePago(@PathVariable Long id) {
            return metodoDePagoService.findMetodoDePago(id);
        }

        // crear MetodoDePago
        @PostMapping("/save")
        public ResponseEntity<MetodoDePago> createMetodoDePago(@RequestBody MetodoDePagoDTO metodoDePagoDTO) {
            return ResponseEntity.ok(metodoDePagoService.saveMetodoDePago(metodoDePagoDTO));
        }

        //  eliminar MetodoDePago
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteMetodoDePago(@PathVariable Long id) {
            metodoDePagoService.deleteMetodoDePago(id);
            return ResponseEntity.ok("MetodoDePago eliminado");
        }

        // editar MetodoDePago
        @PatchMapping("/update/{id}")
        public ResponseEntity<MetodoDePago> updateMetodoDePago(@PathVariable Long id, @RequestBody MetodoDePagoDTO metodoDePagoDTO) {
            return ResponseEntity.ok(metodoDePagoService.updateMetodoDePago(id, metodoDePagoDTO));

        }
    }


