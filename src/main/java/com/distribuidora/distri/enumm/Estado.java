package com.distribuidora.distri.enumm;

// ESTADO DEL PEDIDO
public enum Estado {

    PENDIENTE,
    FACTURADO;

    public static Estado getPendiente() {
        return PENDIENTE;
    }
    public static Estado getFacturado() {
        return FACTURADO;
    }

}

