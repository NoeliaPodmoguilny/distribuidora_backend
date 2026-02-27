package com.distribuidora.distri.model;

import com.distribuidora.distri.enumm.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    private LocalDate fechaPedido;
    private Double montoTotalPedido;
    @Enumerated(EnumType.STRING)
    private Estado estado;

    //relacion con cliente
    @ManyToOne
    private Cliente cliente;

    //relacion con empleados
    @ManyToMany
    @JoinTable(name = "pedidos_empleados",
            joinColumns = @JoinColumn(name = "idPedido"),
            inverseJoinColumns = @JoinColumn(name = "idEmpleado"))
    private List<Empleado> empleados = new ArrayList<>();

}
