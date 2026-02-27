package com.distribuidora.distri.enumm;

public enum EstadoReparto {

        PENDIENTE,   // Creado pero aún sin salida (preparando carga o esperando facturas)
        FINALIZADO,  // Todas las entregas procesadas (entregadas o no entregadas)
        CANCELADO;   // Reparto anulado (por error, accidente o reprogramación)

    public static EstadoReparto getPendiente() {
        return PENDIENTE;
    }
    public static EstadoReparto getFinalizado() {
        return FINALIZADO;
    }
    public static EstadoReparto getCancelado() {
        return CANCELADO;
    }

    }


