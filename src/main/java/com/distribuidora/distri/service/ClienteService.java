package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.ClienteDTO;
import com.distribuidora.distri.model.Cliente;
import com.distribuidora.distri.repository.IClienteRepository;
import com.distribuidora.distri.service.interfaz.IClienteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.distribuidora.distri.helper.UpdateHelper.setIf;
import static com.distribuidora.distri.helper.UpdateHelper.setIfNotNull;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findCliente(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
    }

    @Override
    public Cliente findClienteBynombreCliente(String nombreCliente) {
        return clienteRepository.findByNombre(nombreCliente);
    }

    @Override
    public Cliente saveCliente(@Valid ClienteDTO clienteDTO) {

        Cliente cl = new Cliente();
        // si no son null
        setIfNotNull(clienteDTO.getNombre(), cl::setNombre);
        setIfNotNull(clienteDTO.getApellido(), cl::setApellido);
        setIfNotNull(clienteDTO.getEmail(), cl::setEmail);
        setIfNotNull(clienteDTO.getCalle(), cl::setCalle);
        setIfNotNull(clienteDTO.getLocalidad(), cl::setLocalidad);
        setIfNotNull(clienteDTO.getProvincia(), cl::setProvincia);
        setIfNotNull(clienteDTO.getTelefono(), cl::setTelefono);
        setIfNotNull(clienteDTO.getRazonSocial(), cl::setRazonSocial);
        setIfNotNull(clienteDTO.getNombreNegocio(), cl::setNombreNegocio);
        setIfNotNull(clienteDTO.getAltura(), cl::setAltura);
        setIfNotNull(clienteDTO.getTipoDocumento(), cl::setTipoDocumento);
        setIfNotNull(clienteDTO.getZona(), cl::setZona);

        if (clienteRepository.existsByNumeroDocumento(clienteDTO.getNumeroDocumento())) {
            throw new IllegalArgumentException("El DNI ya existe en la base de datos");
        }else{
            setIfNotNull(clienteDTO.getNumeroDocumento(), cl::setNumeroDocumento);
        }
        setIf(clienteDTO.getCuitCuil(), cuit -> cuit.matches("\\d{11}"), cl::setCuitCuil);

        setIfNotNull(clienteDTO.getCategoriaCliente(), cl::setCategoriaCliente);
        setIfNotNull(clienteDTO.getTipoCliente(), cl::setTipoCliente);
        setIfNotNull(clienteDTO.getDescripcionCategoria(), cl::setDescripcionCategoria);
        setIfNotNull(clienteDTO.getTipoPersona(), cl::setTipoPersona);

        return clienteRepository.save(cl);
    }

    @Override
    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }
        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente updateCliente(Long id, @Valid ClienteDTO clienteDTO) {

        Cliente cl = this.findCliente(id);
        setIfNotNull(clienteDTO.getNombre(), cl::setNombre);
        setIfNotNull(clienteDTO.getApellido(), cl::setApellido);
        setIfNotNull(clienteDTO.getEmail(), cl::setEmail);
        setIfNotNull(clienteDTO.getCalle(), cl::setCalle);
        setIfNotNull(clienteDTO.getLocalidad(), cl::setLocalidad);
        setIfNotNull(clienteDTO.getProvincia(), cl::setProvincia);
        setIfNotNull(clienteDTO.getTelefono(), cl::setTelefono);
        setIfNotNull(clienteDTO.getRazonSocial(), cl::setRazonSocial);
        setIfNotNull(clienteDTO.getNombreNegocio(), cl::setNombreNegocio);
        setIfNotNull(clienteDTO.getAltura(), cl::setAltura);
        setIfNotNull(clienteDTO.getTipoDocumento(), cl::setTipoDocumento);
        setIfNotNull(clienteDTO.getNumeroDocumento(), cl::setNumeroDocumento);
        setIfNotNull(clienteDTO.getZona(), cl::setZona);


        setIf(clienteDTO.getCuitCuil(), cuit -> cuit.matches("\\d{11}"), cl::setCuitCuil);

        setIfNotNull(clienteDTO.getCategoriaCliente(), cl::setCategoriaCliente);
        setIfNotNull(clienteDTO.getTipoCliente(), cl::setTipoCliente);
        setIfNotNull(clienteDTO.getDescripcionCategoria(), cl::setDescripcionCategoria);
        setIfNotNull(clienteDTO.getTipoPersona(), cl::setTipoPersona);

        return clienteRepository.save(cl);
    }
}
