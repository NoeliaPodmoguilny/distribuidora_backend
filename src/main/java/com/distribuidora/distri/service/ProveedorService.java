package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.ProveedorDTO;
import com.distribuidora.distri.model.Proveedor;
import com.distribuidora.distri.repository.IProveedorRepository;
import com.distribuidora.distri.service.interfaz.IProveedorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService implements IProveedorService {


    @Autowired
    private IProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> getProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor findProveedor(Long id) {
        return proveedorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado"));
    }

    @Override
    public Proveedor findProveedorBynombreProveedor(String nombreProveedor) {
        return proveedorRepository.findByNombre(nombreProveedor);
    }

    @Override
    public Proveedor saveProveedor(@Valid ProveedorDTO proveedorDTO) {
        Proveedor prov = new Proveedor();
        prov.setNombre(proveedorDTO.getNombre());
        prov.setApellido(proveedorDTO.getApellido());
        prov.setEmail(proveedorDTO.getEmail());
        prov.setCalle(proveedorDTO.getCalle());
        prov.setAltura(proveedorDTO.getAltura());
        prov.setLocalidad(proveedorDTO.getLocalidad());
        prov.setProvincia(proveedorDTO.getProvincia());
        prov.setNumeroDocumento(proveedorDTO.getNumeroDocumento());
        prov.setTipoDocumento(proveedorDTO.getTipoDocumento());
        prov.setTelefono(proveedorDTO.getTelefono());

        prov.setCbu(proveedorDTO.getCbu());
        prov.setCuit(proveedorDTO.getCuit());
        prov.setRazonSocial(proveedorDTO.getRazonSocial());
        prov.setTipoPersona(proveedorDTO.getTipoPersona());


        return proveedorRepository.save(prov);
      
    }

    @Override
    public void deleteProveedor(Long id) {
        if (!proveedorRepository.existsById(id)) {
            throw new RuntimeException("Proveedor no encontrado con id: " + id);
        }
        proveedorRepository.deleteById(id);
    }

    @Override
    public Proveedor updateProveedor(Long id, @Valid ProveedorDTO proveedorDTO) {
        Proveedor prov = this.findProveedor(id);
        prov.setNombre(proveedorDTO.getNombre());
        prov.setApellido(proveedorDTO.getApellido());
        prov.setEmail(proveedorDTO.getEmail());
        prov.setCalle(proveedorDTO.getCalle());
        prov.setAltura(proveedorDTO.getAltura());
        prov.setLocalidad(proveedorDTO.getLocalidad());
        prov.setProvincia(proveedorDTO.getProvincia());
        prov.setNumeroDocumento(proveedorDTO.getNumeroDocumento());
        prov.setTipoDocumento(proveedorDTO.getTipoDocumento());
        prov.setTelefono(proveedorDTO.getTelefono());

        prov.setCbu(proveedorDTO.getCbu());
        prov.setCuit(proveedorDTO.getCuit());
        prov.setRazonSocial(proveedorDTO.getRazonSocial());
        prov.setTipoPersona(proveedorDTO.getTipoPersona());
        return proveedorRepository.save(prov);
    }
}
