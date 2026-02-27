package com.distribuidora.distri.repository;

import com.distribuidora.distri.model.ComboProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComboProductoRepository extends JpaRepository<ComboProducto, Long> {
}
