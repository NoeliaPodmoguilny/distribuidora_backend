
package com.distribuidora.distri.dto.response;

import com.distribuidora.distri.enumm.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepartoResponseDTO {

    // Datos del reparto
    private Long idReparto;
    private EstadoReparto estado;
    private LocalDate fechaReparto;

    //Datos empleado/repartidor
    private Long idRepartidor;
    private String nombreRepartidor;
    private String apellidoRepartidor;
    private ZonaReparto zona;

    // Factura
    private List<FacturaRepartoResponseDTO> facturas;


}
