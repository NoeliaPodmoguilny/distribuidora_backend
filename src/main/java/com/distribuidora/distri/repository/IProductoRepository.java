package com.distribuidora.distri.repository;


import com.distribuidora.distri.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    Producto findBynombreProducto(String nombreProducto);

    Producto findByCodigo(String codigo);

    @Query("SELECT p FROM Producto p " +
            "WHERE (:nombre IS NULL OR LOWER(p.nombreProducto) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "OR (:codigo IS NULL OR LOWER(p.codigo) LIKE LOWER(CONCAT('%', :codigo, '%'))) " +
            "OR (:marca IS NULL OR LOWER(p.marca.nombreMarca) LIKE LOWER(CONCAT('%', :marca, '%'))) " +
            "OR (:categoria IS NULL OR LOWER(p.categoriaProducto.nombreCategoria) LIKE LOWER(CONCAT('%', :categoria, '%')))")
    List<Producto> buscarProductos(@Param("nombre") String nombre,
                                   @Param("codigo") String codigo,
                                   @Param("marca") String marca,
                                   @Param("categoria") String categoria);



}
