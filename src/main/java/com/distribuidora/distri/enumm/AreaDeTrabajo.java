package com.distribuidora.distri.enumm;


public enum AreaDeTrabajo {

    VENTAS,
    ADMINISTRACION,
    COMPRAS,
    DISTRIBUCION,
    DEPOSITO,
    FACTURACION,
    GERENCIA;

    public static AreaDeTrabajo getVentas() {
        return VENTAS;
    }
    public static AreaDeTrabajo getAdministracion() {
        return ADMINISTRACION;
    }
    public static AreaDeTrabajo getCompras() {
        return COMPRAS;
    }
    public static AreaDeTrabajo getDistribucion() {
        return DISTRIBUCION;
    }
    public static AreaDeTrabajo getDeposito() {
        return DEPOSITO;
    }
    public static AreaDeTrabajo getFacturacion() {
        return FACTURACION;
    }
    public static AreaDeTrabajo getGerencia() {
        return GERENCIA;
    }


}
