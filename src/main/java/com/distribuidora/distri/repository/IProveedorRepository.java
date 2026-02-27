package com.distribuidora.distri.repository;

import com.distribuidora.distri.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProveedorRepository extends JpaRepository<Proveedor, Long> {

    Proveedor findByNombre(String nombreProveedor);
}
