package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.PermisoDTO;
import com.distribuidora.distri.model.Permiso;

import java.util.List;

public interface IPermisoService {
    List<Permiso> getPermisos();

    Permiso findPermiso(Long id);

    Permiso savePermiso(PermisoDTO permisoDTO);

    void deletePermiso(Long id);

    Permiso updatePermiso(Long id, PermisoDTO permisoDTO);
}
