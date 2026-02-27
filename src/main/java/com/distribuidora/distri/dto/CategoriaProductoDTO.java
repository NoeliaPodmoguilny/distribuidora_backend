package com.distribuidora.distri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriaProductoDTO {

    private Long id;
    private String nombreCategoria;
    private String descripcionCategoria;
}

