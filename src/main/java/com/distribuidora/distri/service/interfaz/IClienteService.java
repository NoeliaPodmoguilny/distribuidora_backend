package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.ClienteDTO;
import com.distribuidora.distri.model.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> getClientes();

    Cliente findCliente(Long id);

    Cliente findClienteBynombreCliente(String nombreCliente);

    Cliente saveCliente(ClienteDTO clienteDTO);

    void deleteCliente(Long id);

    Cliente updateCliente(Long id, ClienteDTO clienteDTO);
}
