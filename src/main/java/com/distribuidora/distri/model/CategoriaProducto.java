package com.distribuidora.distri.model;

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
public class CategoriaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoriaProducto;
    private String nombreCategoria;
    private String descripcionCategoria;




}
