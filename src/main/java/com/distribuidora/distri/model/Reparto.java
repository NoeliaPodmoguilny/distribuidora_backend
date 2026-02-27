package com.distribuidora.distri.model;

import com.distribuidora.distri.enumm.EstadoReparto;
import com.distribuidora.distri.enumm.ZonaReparto;
import com.distribuidora.distri.helper.UpperCaseListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reparto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReparto;

    @Enumerated(EnumType.STRING)
    private EstadoReparto estado;

    //relacion con empleado
    @ManyToOne
    private Empleado empleado;
    private LocalDate fechaReparto;

    @OneToMany
    private List<Factura> facturas;


}
