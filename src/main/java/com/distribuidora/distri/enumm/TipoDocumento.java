package com.distribuidora.distri.enumm;

public enum TipoDocumento {

    DNI,
    PASAPORTE,
    LC;

    public static TipoDocumento getDni() {
        return DNI;
    }
    public static TipoDocumento getPasaporte() {
        return PASAPORTE;
    }
    public static TipoDocumento getLc() {
        return LC;
    }
}
