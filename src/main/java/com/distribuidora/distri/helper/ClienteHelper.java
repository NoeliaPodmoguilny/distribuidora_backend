package com.distribuidora.distri.helper;

import com.distribuidora.distri.dto.ClienteDTO;
import com.distribuidora.distri.model.Cliente;

public class ClienteHelper {

    private ClienteDTO convertirCliente(Cliente cliente) {
        if (cliente == null) return null;
        ClienteDTO dto = new ClienteDTO();
        dto.setIdPersona(cliente.getIdPersona());
        dto.setNombre(cliente.getNombre());
        // .... todos los set...
        return dto;
    }



}
