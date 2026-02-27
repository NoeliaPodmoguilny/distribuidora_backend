package com.distribuidora.distri.mapper;

import com.distribuidora.distri.dto.response.EmpleadoResponseDTO;
import com.distribuidora.distri.model.Empleado;
import com.distribuidora.distri.model.Permiso;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmpleadoMapper {

    public EmpleadoResponseDTO toDTO(Empleado em) {
        if (em == null) return null;

        EmpleadoResponseDTO dto = new EmpleadoResponseDTO();

        dto.setIdPersona(em.getIdPersona());
        dto.setNombre(em.getNombre());
        dto.setApellido(em.getApellido());
        dto.setEmail(em.getEmail());
        dto.setCalle(em.getCalle());
        dto.setLocalidad(em.getLocalidad());
        dto.setProvincia(em.getProvincia());
        dto.setTelefono(em.getTelefono());
        dto.setTipoPersona(em.getTipoPersona());
        dto.setPosicion(em.getPosicion());
        dto.setUsuario(em.getUsuario());
        dto.setZona(em.getZona());
        dto.setAreaDeTrabajo(em.getAreaDeTrabajo());
        dto.setCuil(em.getCuil());
        dto.setAltura(em.getAltura());
        dto.setTipoDocumento(em.getTipoDocumento());
        dto.setNumeroDocumento(em.getNumeroDocumento());
        dto.setActivo(em.getActivo());

        // Permisos: devuelvo solo IDs
        if (em.getPermisos() != null) {
            dto.setPermisosDTO(
                    em.getPermisos().stream()
                            .map(Permiso::getIdPermiso)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public List<EmpleadoResponseDTO> toDTOList(List<Empleado> empleados) {
        return empleados.stream()
                .map(this::toDTO)
                .toList();
    }
}


