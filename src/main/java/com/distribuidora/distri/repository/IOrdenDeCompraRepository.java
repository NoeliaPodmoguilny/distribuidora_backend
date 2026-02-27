package com.distribuidora.distri.repository;

import com.distribuidora.distri.model.OrdenDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenDeCompraRepository extends JpaRepository<OrdenDeCompra, Long> {
}
