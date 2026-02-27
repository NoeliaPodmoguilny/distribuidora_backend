package com.distribuidora.distri.dto;

import com.distribuidora.distri.enumm.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDTO {

    private Long idPersona;
    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(
            regexp = "^[\\p{L} \\.'´-]+$",
            message = "El nombre contiene caracteres inválidos." +
                    "Sólo se acepta: "+
                    "Apóstrofo - " +
                    "Guiones - " +
                    "Nombres internacionales - " +
                    "Espacios - " +
                    "Tildes"
    )
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio")
    @Pattern(
            regexp = "^[\\p{L} \\.'´-]+$",
            message = "El apellido contiene caracteres inválidos." +
                    "Sólo se acepta: "+
                    "Apóstrofo - " +
                    "Guiones - " +
                    "Nombres internacionales - " +
                    "Espacios - " +
                    "Tildes"
    )
    private String apellido;
    private TipoPersona tipoPersona;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private String email;
    private String telefono;
    private String altura;
    private String calle;
    private String localidad;
    private String provincia;

    private CategoriaCliente categoriaCliente;
    private String descripcionCategoria;
    private String nombreNegocio;
    private String razonSocial;
    private TipoCliente tipoCliente;
    private String cuitCuil;
    private ZonaReparto zona;
}