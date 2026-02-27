package com.distribuidora.distri.enumm;

public enum ZonaReparto {

    NORTE,
    SUR,
    ESTE,
    OESTE;

    public static ZonaReparto getNorte() {
        return NORTE;
    }
    public static ZonaReparto getSur() {
        return SUR;
    }
    public static ZonaReparto getEste(){ return ESTE;}
    public static ZonaReparto getOeste() {
        return OESTE;
    }

}
