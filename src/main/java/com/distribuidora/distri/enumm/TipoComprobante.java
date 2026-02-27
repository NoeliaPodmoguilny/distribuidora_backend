package com.distribuidora.distri.enumm;

public enum TipoComprobante {

    FACTURA,
    NC,
    OC;


    public static TipoComprobante getFactura() {
        return FACTURA;
    }
    public static TipoComprobante getNc() {
        return NC;
    }
    public static TipoComprobante getOc() {
        return OC;
    }




}
