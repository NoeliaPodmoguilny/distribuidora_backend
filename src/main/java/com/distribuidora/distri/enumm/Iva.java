package com.distribuidora.distri.enumm;

public enum Iva {

    IVA_21(21),
    IVA_105(10.5),
    IVA_0(0);

    private final double porcentaje;

    Iva(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public double calcularIVA(double base) {
        return base * (porcentaje / 100);
    }

    public double calcularConIVA(double base) {
        return base + calcularIVA(base);
    }
}
