package com.distribuidora.distri.model;

import com.distribuidora.distri.enumm.TipoMetodoPago;
import com.distribuidora.distri.helper.UpperCaseListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(UpperCaseListener.class)
public class MetodoDePago {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMetodoPago;
    @Enumerated(EnumType.STRING)
    private TipoMetodoPago tipoMetodoPago;
    private String descripcionPago;


}
