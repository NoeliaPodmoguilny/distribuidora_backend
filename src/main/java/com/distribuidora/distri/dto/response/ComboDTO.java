package com.distribuidora.distri.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComboDTO {

    private Long id;
    private String descripcion;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private Double porcentajeDescuento;

    private List<ComboProductoDTO> comboProductosDTO;

}
