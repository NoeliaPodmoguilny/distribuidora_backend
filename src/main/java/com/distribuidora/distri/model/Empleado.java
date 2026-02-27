package com.distribuidora.distri.model;

import com.distribuidora.distri.enumm.AreaDeTrabajo;
import com.distribuidora.distri.enumm.Posicion;
import com.distribuidora.distri.enumm.ZonaReparto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Empleado extends Persona{

    @Enumerated(EnumType.STRING)
    private Posicion posicion;
    @Column(unique = true, nullable = false)
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúñÑ '´-]+$",
            message = "El nombre de usuario contiene caracteres inválidos." +
                    "\nSólo se acepta:\n"+
                    "Apóstrofo\n" +
                    "Guiones\n" +
                    "Nombres internacionales\n" +
                    "Espacios\n" +
                    "Tildes"
    )
    private String usuario;
    private String contraseña;
    // Activo si está dado de alta
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private AreaDeTrabajo areaDeTrabajo;
    @Column(unique = true, nullable = false)
    private String cuil;

    @Enumerated(EnumType.STRING)
    private ZonaReparto zona;

    //Relacion con permiso
    @ManyToMany
    @JoinTable(name = "empleados_permisos",
            joinColumns = @JoinColumn(name = "idEmpleado"),
            inverseJoinColumns = @JoinColumn(name = "idPermiso"))
    private List<Permiso> permisos;




}
