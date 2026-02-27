package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.request.ActivateEmpleadoDTO;
import com.distribuidora.distri.dto.request.EmpleadoRequestDTO;
import com.distribuidora.distri.dto.response.EmpleadoResponseDTO;

import java.util.List;

public interface IEmpleadoService {
    List<EmpleadoResponseDTO> getEmpleados();

    EmpleadoResponseDTO findEmpleado(Long id);

    EmpleadoResponseDTO findEmpleadoByNombre(String nombreEmpleado);

    String saveEmpleado(EmpleadoRequestDTO empleadoDTO);

    void deleteEmpleado(Long id);

    EmpleadoResponseDTO updateEmpleado(Long id, EmpleadoRequestDTO empleadoDTO);

    void isActivo(Long idSeleccionado, ActivateEmpleadoDTO activateEmpleadoDTO);

}
