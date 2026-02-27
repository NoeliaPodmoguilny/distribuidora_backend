package com.distribuidora.distri.enumm;

public enum EstadoInventario {


    DISPONIBLE,
    ANULADO,
    VENCIDO,
    AGOTADO;

    public static EstadoInventario getDisponible() {
        return DISPONIBLE;
    }
    public static EstadoInventario getAnulado() {
        return ANULADO;
    }
    public static EstadoInventario getAgotado() {
        return AGOTADO;
    }
    public static EstadoInventario getVencido() {
        return VENCIDO;
    }

}
