package com.distribuidora.distri.repository;

import com.distribuidora.distri.model.Reparto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IRepartoRepository extends JpaRepository<Reparto, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Reparto r SET r.estado='FINALIZADO' WHERE r.idReparto=:idReparto")
    void cambiarFinalizado(@Param("idReparto") Long reparto);


}
