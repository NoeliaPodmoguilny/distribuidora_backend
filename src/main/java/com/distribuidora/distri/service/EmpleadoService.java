package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.request.ActivateEmpleadoDTO;
import com.distribuidora.distri.dto.request.EmpleadoRequestDTO;
import com.distribuidora.distri.dto.response.EmpleadoResponseDTO;
import com.distribuidora.distri.mapper.EmpleadoMapper;
import com.distribuidora.distri.model.Empleado;
import com.distribuidora.distri.model.Permiso;
import com.distribuidora.distri.repository.IEmpleadoRepository;
import com.distribuidora.distri.repository.IPermisoRepository;
import com.distribuidora.distri.service.interfaz.IEmpleadoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.distribuidora.distri.helper.UpdateHelper.setIf;
import static com.distribuidora.distri.helper.UpdateHelper.setIfNotNull;

@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired private IEmpleadoRepository empleadoRepository;
    @Autowired private IPermisoRepository permisoRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private EmpleadoMapper mapper;

    @Override
    public List<EmpleadoResponseDTO> getEmpleados() {
        return mapper.toDTOList(empleadoRepository.findAll());
    }

    @Override
    public EmpleadoResponseDTO findEmpleado(Long id) {
        Empleado em = empleadoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));
        return mapper.toDTO(em);
    }

    @Override
    public EmpleadoResponseDTO findEmpleadoByNombre(String nombreEmpleado) {
        Empleado em = empleadoRepository.findByNombre(nombreEmpleado);
        return mapper.toDTO(em);
    }

    @Override
    public void isActivo(Long idSeleccionado, ActivateEmpleadoDTO activateEmpleadoDTO) {
        // en caso de querer desactivarse el admin a si mismo, tira error
        if (activateEmpleadoDTO.getIdLogueado() != null &&
                activateEmpleadoDTO.getIdLogueado().equals(idSeleccionado)) {
            throw new IllegalArgumentException("No podés desactivar tu propio usuario");
        }
        Empleado em = empleadoRepository.findById(idSeleccionado).orElseThrow(
                () -> new IllegalArgumentException("Empleado no encontrado"));
        em.setActivo(activateEmpleadoDTO.getEstado());
        empleadoRepository.save(em);
    }

    @Override
    public String saveEmpleado(@Valid EmpleadoRequestDTO empleadoDTO) {
        Empleado em = new Empleado();

        // si no son null
        setIfNotNull(empleadoDTO.getNombre(), em::setNombre);
        setIfNotNull(empleadoDTO.getApellido(), em::setApellido);
        setIfNotNull(empleadoDTO.getEmail(), em::setEmail);
        setIfNotNull(empleadoDTO.getCalle(), em::setCalle);
        setIfNotNull(empleadoDTO.getLocalidad(), em::setLocalidad);
        setIfNotNull(empleadoDTO.getProvincia(), em::setProvincia);
        setIfNotNull(empleadoDTO.getTelefono(), em::setTelefono);
        setIfNotNull(empleadoDTO.getTipoPersona(), em::setTipoPersona);
        setIfNotNull(empleadoDTO.getPosicion(), em::setPosicion);
        setIfNotNull(empleadoDTO.getUsuario(), em::setUsuario);
        setIfNotNull(empleadoDTO.getZona(), em::setZona);
        // encriptar pass
        em.setContraseña(passwordEncoder.encode(empleadoDTO.getContraseña()));
        setIfNotNull(empleadoDTO.getAreaDeTrabajo(), em::setAreaDeTrabajo);
        setIfNotNull(empleadoDTO.getCuil(), em::setCuil);
        setIfNotNull(empleadoDTO.getAltura(), em::setAltura);
        setIfNotNull(empleadoDTO.getTipoDocumento(), em::setTipoDocumento);

        if (empleadoRepository.existsByNumeroDocumento(empleadoDTO.getNumeroDocumento())) {
            throw new IllegalArgumentException("El DNI ya existe en la base de datos");
        }else{
            setIfNotNull(empleadoDTO.getNumeroDocumento(), em::setNumeroDocumento);
        }
        setIf(empleadoDTO.getCuil(), cuit -> cuit.matches("\\d{11}"), em::setCuil);
        setIfNotNull(empleadoDTO.getActivo(), em::setActivo);

        // Relación con permisos
        // Si no tiene asignado un permiso, tira un error
        if (empleadoDTO.getPermisosDTO() == null || empleadoDTO.getPermisosDTO().isEmpty()) {
            throw new IllegalArgumentException("El empleado debe tener al menos un permiso asignado");
        }
        // si esta ok, se guarda el o los permisos asignados
        List<Permiso> permisos = empleadoDTO.getPermisosDTO().stream()
                .map(permisoId -> permisoRepository.findById(permisoId)
                        .orElseThrow(() -> new RuntimeException(
                                "Permiso no encontrado con id: " + permisoId)))
                .collect(Collectors.toList());
        setIfNotNull(permisos, em::setPermisos);

        empleadoRepository.save(em);
        return "Guardado";
    }

    @Override
    public void deleteEmpleado(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new RuntimeException("Empleado no encontrado con el id: " + id);
        }
        empleadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public EmpleadoResponseDTO updateEmpleado(Long id, @Valid EmpleadoRequestDTO empleadoDTO) {

        Empleado em = empleadoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Empleado no encontrado"));

        // si no son null
        setIfNotNull(empleadoDTO.getNombre(), em::setNombre);
        setIfNotNull(empleadoDTO.getApellido(), em::setApellido);
        setIfNotNull(empleadoDTO.getEmail(), em::setEmail);
        setIfNotNull(empleadoDTO.getCalle(), em::setCalle);
        setIfNotNull(empleadoDTO.getLocalidad(), em::setLocalidad);
        setIfNotNull(empleadoDTO.getProvincia(), em::setProvincia);
        setIfNotNull(empleadoDTO.getTelefono(), em::setTelefono);
        setIfNotNull(empleadoDTO.getTipoPersona(), em::setTipoPersona);
        setIfNotNull(empleadoDTO.getPosicion(), em::setPosicion);
        setIfNotNull(empleadoDTO.getUsuario(), em::setUsuario);
        setIfNotNull(empleadoDTO.getZona(), em::setZona);

        // encriptar pass
        setIfNotNull(empleadoDTO.getContraseña(),
                pass -> em.setContraseña(passwordEncoder.encode(pass))
        );
        setIfNotNull(empleadoDTO.getAreaDeTrabajo(), em::setAreaDeTrabajo);
        setIfNotNull(empleadoDTO.getCuil(), em::setCuil);
        setIfNotNull(empleadoDTO.getAltura(), em::setAltura);
        setIfNotNull(empleadoDTO.getNumeroDocumento(), em::setNumeroDocumento);
        setIf(empleadoDTO.getCuil(), cuit -> cuit.matches("\\d{11}"), em::setCuil);
        setIfNotNull(empleadoDTO.getActivo(), em::setActivo);

        // Relación con permisos
        // Si no tiene asignado un permiso, tira un error
        if (empleadoDTO.getPermisosDTO() == null || empleadoDTO.getPermisosDTO().isEmpty()) {
            throw new IllegalArgumentException("El empleado debe tener al menos un permiso asignado");
        }
        // si esta ok, se guarda el o los permisos asignados
        List<Permiso> permisos = empleadoDTO.getPermisosDTO().stream()
                .map(permisoId -> permisoRepository.findById(permisoId)
                        .orElseThrow(() -> new RuntimeException(
                                "Permiso no encontrado con id: " + permisoId)))
                .collect(Collectors.toList());
        setIfNotNull(permisos, em::setPermisos);
        // Guardo los nuevos valores del Empleado
        empleadoRepository.save(em);
        //devuelvo un EmpeladoResponseDTO
        return mapper.toDTO(em);
    }

}
