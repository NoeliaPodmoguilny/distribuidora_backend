package com.distribuidora.distri.enumm;

public enum Posicion {

    VENDEDOR,
    ADMINISTRATIVO,
    REPARTIDOR,
    TESORERO,
    SUPERVISOR,
    ENCARGADO_DEPOSITO,
    CONTADOR,
    TECNICO;

    public static Posicion getVendedor() {
        return VENDEDOR;
    }
    public static Posicion getAdministrativo() {
        return ADMINISTRATIVO;
    }
    public static Posicion getRepartidor() {
        return REPARTIDOR;
    }
    public static Posicion getTesorero() {
        return TESORERO;
    }
    public static Posicion getSupervisor() {
        return SUPERVISOR;
    }
    public static Posicion getEncargadoDeposito() {
        return ENCARGADO_DEPOSITO;
    }
    public static Posicion getContador() {
        return CONTADOR;
    }
    public static Posicion getTecnico() {
        return TECNICO;
    }


}
