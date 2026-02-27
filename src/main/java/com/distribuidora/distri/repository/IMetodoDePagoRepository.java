package com.distribuidora.distri.repository;

import com.distribuidora.distri.model.MetodoDePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMetodoDePagoRepository extends JpaRepository<MetodoDePago, Long> {
}
