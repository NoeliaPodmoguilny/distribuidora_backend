package com.distribuidora.distri.enumm;



public enum TipoCliente {

    MAYORISTA,
    MINORISTA;

    public static TipoCliente getMayorista() {
        return MAYORISTA;
    }
    public static TipoCliente getMinorista() {
        return MINORISTA;
    }

}
