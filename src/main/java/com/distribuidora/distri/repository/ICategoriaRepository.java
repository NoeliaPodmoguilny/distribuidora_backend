package com.distribuidora.distri.repository;

import com.distribuidora.distri.model.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository extends JpaRepository<CategoriaProducto, Long> {
    
    public CategoriaProducto findCategoriaProductoByNombreCategoria(String nombreCategoria);
}
