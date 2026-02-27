package com.distribuidora.distri.repository;

import com.distribuidora.distri.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMarcaRepository extends JpaRepository<Marca, Long> {
    Marca findMarcaBynombreMarca(String nombreMarca);
}
