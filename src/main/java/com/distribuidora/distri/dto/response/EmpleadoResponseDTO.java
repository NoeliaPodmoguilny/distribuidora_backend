package com.distribuidora.distri.dto.response;

import com.distribuidora.distri.enumm.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpleadoResponseDTO {

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
        private TipoDocumento tipoDocumento;
        private String numeroDocumento;
        private String email;
        private String telefono;
        private String altura;
        private String calle;
        private String localidad;
        private String provincia;
        private TipoPersona tipoPersona;

        private Posicion posicion;
        @NotBlank(message = "El nombre de usuario es obligatorio")
        @Pattern(
                regexp = "^[\\p{L} \\.'´-]+$",
                message = "El usuario contiene caracteres inválidos." +
                        "Sólo se acepta: "+
                        "Apóstrofo - " +
                        "Guiones - " +
                        "Nombres internacionales - " +
                        "Espacios - " +
                        "Tildes"
        )
        private String usuario;
        private Boolean activo;
        private AreaDeTrabajo areaDeTrabajo;
        private String cuil;
        private ZonaReparto zona;
        @NotNull(message = "La lista de permisos es obligatoria")
        @NotEmpty(message = "Debe tener al menos un permiso")
        private List<Long> permisosDTO = new ArrayList<>();


}
