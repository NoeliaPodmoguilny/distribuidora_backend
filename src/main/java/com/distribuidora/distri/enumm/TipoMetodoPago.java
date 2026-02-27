package com.distribuidora.distri.enumm;

public enum TipoMetodoPago {

    BILLETERA_VIRTUAL,
    EFECTIVO,
    DEBITO,
    CUENTA_CORRIENTE,
    TRANSFERENCIA,
    TARJETA;

    public static TipoMetodoPago getBilleteraVirtual() {
        return BILLETERA_VIRTUAL;
    }

    public static TipoMetodoPago getEfectivo() {
        return EFECTIVO;
    }
    public static TipoMetodoPago getDebito() {
        return DEBITO;
    }
    public static TipoMetodoPago getCuentaCorriente() {
        return CUENTA_CORRIENTE;
    }
    public static TipoMetodoPago getTransferencia() {
        return TRANSFERENCIA;
    }
    public static TipoMetodoPago getTarjeta() {
        return TARJETA;
    }

}
