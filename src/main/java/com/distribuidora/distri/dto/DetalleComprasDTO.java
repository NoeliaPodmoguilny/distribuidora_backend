package com.distribuidora.distri.dto;

import com.distribuidora.distri.model.OrdenDeCompra;
import com.distribuidora.distri.model.Producto;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetalleComprasDTO {

    private Long id;
    private Long ordenDeCompraDTO;
    private Long productoDTO;
    private int cantidad;


}
