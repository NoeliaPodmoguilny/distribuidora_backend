package com.distribuidora.distri.dto;

import com.distribuidora.distri.enumm.Iva;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

        private Long id;
        private String codigo;
        @NotBlank(message = "El nombre del producto es obligatorio")
        @Pattern(
                regexp = "^[\\p{L}0-9 \\.'´-]+$",
                message = "El nombre contiene caracteres inválidos"
        )
        private String nombreProducto;
        private Double precioUnitario;
        private Double precioBase;
        private Double porcentajeDeGanacia;
        private Iva iva;
        private int stockMinimo;

        //Relaciones con otras tablas (sólo los id)
        private Long categoriaProducto;
        private Long marca;
        private String imagen;


}


