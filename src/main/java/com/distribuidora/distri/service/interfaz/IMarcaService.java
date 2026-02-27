package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.MarcaDTO;
import com.distribuidora.distri.model.Marca;

import java.util.List;

public interface IMarcaService {
    
    List<Marca> getMarcas();

    Marca findMarca(Long id);

    Marca findMarcaBynombreMarca(String nombreMarca);

    Marca saveMarca(MarcaDTO marcaDTO);

    void deleteMarca(Long id);

    Marca updateMarca(Long id, MarcaDTO marcaDTO);
}
