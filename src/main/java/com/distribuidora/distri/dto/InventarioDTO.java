package com.distribuidora.distri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventarioDTO {

    // Campos de la clave compuesta (InventarioId)
    private Long idProducto;
    private LocalDate fechaVencimiento;

    private int stockActual;

}
