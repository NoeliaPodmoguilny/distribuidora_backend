package com.distribuidora.distri.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivateEmpleadoDTO {

    private Long idLogueado;
    private Boolean estado;

}
