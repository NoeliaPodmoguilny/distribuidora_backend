package com.distribuidora.distri.repository;

import com.distribuidora.distri.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {
    public Empleado findByNombre(String nombreEmpleado);

    boolean existsByNumeroDocumento(String numeroDocumento);

    Optional<Empleado> findEmpleadoEntityByUsuario(String username);
}
