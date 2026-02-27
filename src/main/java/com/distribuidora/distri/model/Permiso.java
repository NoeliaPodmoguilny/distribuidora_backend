package com.distribuidora.distri.model;

import com.distribuidora.distri.helper.UpperCaseListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(UpperCaseListener.class)
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermiso;
    @Column(unique = true, nullable = false)
    private String DescripcionPermiso;

}
