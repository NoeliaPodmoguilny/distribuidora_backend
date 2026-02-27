package com.distribuidora.distri.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Proveedor extends  Persona {

    @Column(unique = true, nullable = false)
    private String razonSocial;
    @Column(unique = true, nullable = false)
    private String cbu;
    @Column(unique = true, nullable = false)
    private String cuit;

}
