package com.distribuidora.distri.repository;

import com.distribuidora.distri.model.FacturaMetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacturaMetodoPagoRepository extends JpaRepository<FacturaMetodoPago, Long> {

}
