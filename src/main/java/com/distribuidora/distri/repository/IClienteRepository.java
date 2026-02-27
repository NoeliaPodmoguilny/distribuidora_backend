package com.distribuidora.distri.repository;

import com.distribuidora.distri.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
    public Cliente findByNombre(String nombreCliente);
    boolean existsByNumeroDocumento(String numeroDocumento);
}
