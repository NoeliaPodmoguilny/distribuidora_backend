package com.distribuidora.distri.enumm;

public enum Motivo {
    DEVOLUCION_TOTAL,
    DEVOLUCION_PARCIAL,
    PRODUCTO_VENCIDO,
    ERROR_ENVIO,
    ERROR_PRECIO,
    DESCUENTO_POSTERIOR,
    DUPLICIDAD_FACTURA,
    ERROR_COMPROBANTE,
    SIN_DINERO,
    PRODUCTO_DAÑADO,
    MAL_FACTURADO;

    public static Motivo getDevolucionTotal() {
        return DEVOLUCION_TOTAL;
    }
    public static Motivo getDevolucionParcial() {
        return DEVOLUCION_PARCIAL;
    }
    public static Motivo getProductoVencido() {
        return PRODUCTO_VENCIDO;
    }
    public static Motivo getErrorEnvio() {
        return ERROR_ENVIO;
    }
    public static Motivo getErrorPrecio() {
        return ERROR_PRECIO;
    }
    public static Motivo getDescuentoPosterior() {
        return DESCUENTO_POSTERIOR;
    }
    public static Motivo getDuplicidadFactura() {
        return DUPLICIDAD_FACTURA;
    }
    public static Motivo getErrorComprobante() {
        return ERROR_COMPROBANTE;
    }
    public static Motivo getSinDinero() {
        return SIN_DINERO;
    }
    public static Motivo getProductoDañado() {
        return PRODUCTO_DAÑADO;
    }
    public static Motivo getMalFacturado() {
        return MAL_FACTURADO;
    }


}
