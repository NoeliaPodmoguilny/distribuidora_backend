package com.distribuidora.distri.enumm;

public enum TipoTransaccion {

    EGRESOS,
    INGRESOS;

    public static TipoTransaccion getEgresos() {
        return EGRESOS;
    }
    public static TipoTransaccion getIngresos() {
        return INGRESOS;
    }


}
