package com.distribuidora.distri.repository;

import com.distribuidora.distri.model.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComboRepository extends JpaRepository<Combo, Long> {
}
