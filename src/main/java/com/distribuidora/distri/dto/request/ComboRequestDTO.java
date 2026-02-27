package com.distribuidora.distri.dto.request;

import com.distribuidora.distri.dto.response.ComboProductoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComboRequestDTO {

    private String descripcion;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private Double porcentajeDescuento;

    private List<ComboProductoDTO> comboProductosDTO = new ArrayList<>();
}
