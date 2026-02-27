package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.PermisoDTO;
import com.distribuidora.distri.model.Permiso;
import com.distribuidora.distri.repository.IPermisoRepository;
import com.distribuidora.distri.service.interfaz.IPermisoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoService implements IPermisoService {

    @Autowired
    private IPermisoRepository permisoRepository;

    @Override
    public List<Permiso> getPermisos() {
        return permisoRepository.findAll();
    }

    @Override
    public Permiso findPermiso(Long id) {
        return permisoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Permiso no encontrado"));
    }

    @Override
    public Permiso savePermiso(PermisoDTO permisoDTO) {

        Permiso per = new Permiso();
        per.setDescripcionPermiso(permisoDTO.getDescripcionPermiso());
        return permisoRepository.save(per) ;
    }

    @Override
    public void deletePermiso(Long id) {
        if (!permisoRepository.existsById(id)) {
            throw new RuntimeException("Permiso no encontrado con el id: " + id);
        }
        permisoRepository.deleteById(id);
    }

    @Override
    public Permiso updatePermiso(Long id, PermisoDTO permisoDTO) {
        Permiso per = this.findPermiso(id);
        per.setDescripcionPermiso(permisoDTO.getDescripcionPermiso());
        return permisoRepository.save(per);
    }
}
