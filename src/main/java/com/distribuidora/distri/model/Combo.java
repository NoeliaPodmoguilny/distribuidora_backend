package com.distribuidora.distri.model;

import com.distribuidora.distri.helper.UpperCaseListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(UpperCaseListener.class)
public class Combo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoCombo;
    @Column(unique = true, nullable = false)
    private String descripcion;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private Double porcentajeDescuento;

    //relacion con ComboProducto
    @OneToMany(mappedBy = "combo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComboProducto> comboProductos = new ArrayList<>();

}
