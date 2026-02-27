package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.MarcaDTO;
import com.distribuidora.distri.model.Marca;
import com.distribuidora.distri.repository.IMarcaRepository;
import com.distribuidora.distri.service.interfaz.IMarcaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService implements IMarcaService {

    @Autowired
    private IMarcaRepository marcaRepository;

    @Override
    public List<Marca> getMarcas() {
        return marcaRepository.findAll();
    }

    @Override
    public Marca findMarca(Long id) {
        return marcaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Marca no encontrada"));
    }

    @Override
    public Marca findMarcaBynombreMarca(String nombreMarca) {
        return marcaRepository.findMarcaBynombreMarca(nombreMarca);
    }

    @Override
    public Marca saveMarca(MarcaDTO marcaDTO) {
        Marca mar = new Marca();
        mar.setNombreMarca(marcaDTO.getNombreMarca());
        return marcaRepository.save(mar);
    }

    @Override
    public void deleteMarca(Long id) {
        if (!marcaRepository.existsById(id)) {
            throw new RuntimeException("Marca no encontrado con el id: " + id);
        }
        marcaRepository.deleteById(id);
    }

    @Override
    public Marca updateMarca(Long id, MarcaDTO marcaDTO) {
        Marca mar = this.findMarca(id);
        mar.setNombreMarca(marcaDTO.getNombreMarca());
        return marcaRepository.save(mar);
    }
}
