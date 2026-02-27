package com.distribuidora.distri.dto;

import com.distribuidora.distri.model.Inventario;
import com.distribuidora.distri.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoInventarioDTO {

        private Integer stockTotal;
        private Inventario inventarioProximo;
    }


