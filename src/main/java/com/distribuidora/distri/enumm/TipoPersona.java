package com.distribuidora.distri.enumm;

public enum TipoPersona {

    EMPLEADO,
    PROVEEDOR,
    CLIENTE;

    public static TipoPersona getEmpleado() {
        return EMPLEADO;
    }
    public static TipoPersona getProveedor() {
        return PROVEEDOR;
    }
    public static TipoPersona getCliente() {
        return CLIENTE;
    }

}
