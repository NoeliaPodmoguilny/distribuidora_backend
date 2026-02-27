package com.distribuidora.distri.model;

import com.distribuidora.distri.enumm.CategoriaCliente;
import com.distribuidora.distri.enumm.TipoCliente;
import com.distribuidora.distri.enumm.ZonaReparto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cliente extends Persona{

    @Enumerated(EnumType.STRING)
    private CategoriaCliente categoriaCliente;
    private String descripcionCategoria;
    private String nombreNegocio;
    @Column(unique = true, nullable = false)
    private String razonSocial;
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;
    private String cuitCuil;
    @Enumerated(EnumType.STRING)
    private ZonaReparto zona;



}
