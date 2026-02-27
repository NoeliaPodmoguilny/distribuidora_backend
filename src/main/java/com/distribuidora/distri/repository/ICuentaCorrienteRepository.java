package com.distribuidora.distri.repository;

import com.distribuidora.distri.model.CuentaCorriente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICuentaCorrienteRepository extends JpaRepository<CuentaCorriente, Long> {

    Optional<CuentaCorriente> findByClienteIdPersona(Long idCliente);
}
