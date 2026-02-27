package com.distribuidora.distri.model;

import com.distribuidora.distri.enumm.TipoDocumento;
import com.distribuidora.distri.enumm.TipoPersona;
import com.distribuidora.distri.helper.UpperCaseListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(UpperCaseListener.class)
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    private String nombre;
    private String apellido;
    @Enumerated(EnumType.STRING)
    private TipoPersona tipoPersona;
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    @Column(unique = true, nullable = false)
    private String numeroDocumento;
    private String email;
    private String telefono;
    private String altura;
    private String calle;
    private String localidad;
    private String provincia;


}
