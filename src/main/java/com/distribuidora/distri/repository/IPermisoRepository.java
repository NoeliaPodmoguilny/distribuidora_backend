package com.distribuidora.distri.repository;


import com.distribuidora.distri.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermisoRepository extends JpaRepository<Permiso, Long> {
}
