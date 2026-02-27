package com.distribuidora.distri.enumm;

public enum EstadoFactura {
        GENERADA,             // Factura generada, pendiente de asignar a un reparto
        ASIGNADA_A_REPARTO,  // Incluida en una hoja de reparto, pendiente de entrega
        ENTREGADA_Y_COBRADA, // Confirmada como entregada al cliente y cobrada
        NO_ENTREGADA,        // Cliente ausente, dirección incorrecta, etc...
        ANULADA,             // Factura cancelada o devuelta, por error o devolución del cliente
        PARCIALMENTE_COBRADA; // Entregada pero con saldo pendiente

    public static EstadoFactura getGenerada() {
        return GENERADA;
    }
    public static EstadoFactura getAsignadaAReparto() {
        return ASIGNADA_A_REPARTO;
    }
    public static EstadoFactura getEntregadaYCobrada() {return ENTREGADA_Y_COBRADA;}
    public static EstadoFactura getNoEntregada() {
        return NO_ENTREGADA;
    }
    public static EstadoFactura getAnulada() {
        return ANULADA;
    }
    public static EstadoFactura getParcialmenteCobrada() {
        return PARCIALMENTE_COBRADA;
    }

    }


