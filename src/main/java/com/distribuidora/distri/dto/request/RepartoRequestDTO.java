package com.distribuidora.distri.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepartoRequestDTO {

    private Long idRepartidor;
    private List<Long> facturasDTO;

}
