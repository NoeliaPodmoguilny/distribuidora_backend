package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.MetodoDePagoDTO;
import com.distribuidora.distri.dto.request.RepartoRequestDTO;
import com.distribuidora.distri.dto.response.FacturaRepartoResponseDTO;
import com.distribuidora.distri.dto.response.FacturaResponseDTO;
import com.distribuidora.distri.dto.response.RepartoResponseDTO;
import com.distribuidora.distri.enumm.EstadoFactura;
import com.distribuidora.distri.enumm.EstadoReparto;
import com.distribuidora.distri.model.*;
import com.distribuidora.distri.repository.IEmpleadoRepository;
import com.distribuidora.distri.repository.IFacturaRepository;
import com.distribuidora.distri.repository.IRepartoRepository;
import com.distribuidora.distri.service.interfaz.IClienteService;
import com.distribuidora.distri.service.interfaz.IRepartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepartoService implements IRepartoService {

    @Autowired private IRepartoRepository repartoRepository;
    @Autowired private IEmpleadoRepository empleadoRepository;
    @Autowired private FacturaService facturaService;
    @Autowired private IFacturaRepository facturaRepository;
    @Autowired private IClienteService clienteService;

    // Buscar lista completa de Repartos existentes
    @Override
    public List<RepartoResponseDTO> getRepartos() {
        List<Reparto> listaRepartos = repartoRepository.findAll();

        List<RepartoResponseDTO> listaRepartoDTO = listaRepartos.stream().map(r ->{
                RepartoResponseDTO repartoDTO = new RepartoResponseDTO();
                repartoDTO.setIdReparto(r.getIdReparto());
                repartoDTO.setEstado(r.getEstado());
                repartoDTO.setNombreRepartidor(r.getEmpleado().getNombre());
                repartoDTO.setApellidoRepartidor(r.getEmpleado().getApellido());
                repartoDTO.setZona(r.getEmpleado().getZona());
                repartoDTO.setFechaReparto(r.getFechaReparto());

                // Creo la lista de facturas y mapeo las facturas
                List<FacturaRepartoResponseDTO> facturasDTO = r.getFacturas().stream().map(f ->{
                    FacturaRepartoResponseDTO facturaDTO = new FacturaRepartoResponseDTO();
                    facturaDTO.setAlturaCliente(f.getPedido().getCliente().getAltura());
                    facturaDTO.setLetra(f.getLetra());
                    facturaDTO.setMontoTotal(f.getMontoTotal());
                    facturaDTO.setCalleCliente(f.getPedido().getCliente().getCalle());
                    facturaDTO.setFechaComprobante(f.getFechaComprobante());
                    facturaDTO.setNumeroComprobante(f.getNumeroComprobante());
                    facturaDTO.setApellidoCliente(f.getPedido().getCliente().getApellido());
                    facturaDTO.setNombreCliente(f.getPedido().getCliente().getNombre());
                    facturaDTO.setTipoComprobante(f.getTipoComprobante());
                    return facturaDTO;
                }).collect(Collectors.toList());
                    repartoDTO.setFacturas(facturasDTO);
                    return repartoDTO;
        }).toList();

        return listaRepartoDTO;
    }

    // Buscar hoja de reparto por id del reparto
    @Override
    public Reparto findReparto(Long id) {
        return repartoRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Reparto no encontrado"));
    }

    // Traer todas las facturas GENERADAS con clientes con la misma zona del repartidor
    @Override
    public List<FacturaResponseDTO> getFacturasParaReparto(Long idRepartidor) {
        // Buscar el repartidor
        Empleado repartidor = empleadoRepository.findById(idRepartidor).orElseThrow(
                () -> new RuntimeException("Empleado no encontrado"));
        if (repartidor == null) {
            throw new IllegalArgumentException("No se encontró el repartidor con ID " + idRepartidor);
        }
        // Traer las facturas de su misma zona que estén pendientes de entrega
        List<Factura> facturas = facturaRepository.getFacturasByZonaAndEstado(
                repartidor.getZona(),
                EstadoFactura.GENERADA);

        // Convertir las facturas a DTO
        List<FacturaResponseDTO> facturasDTO = facturas.stream()
                .map(f -> {
                    FacturaResponseDTO dto = new FacturaResponseDTO();
                    dto.setNumeroComprobante(f.getNumeroComprobante());
                    dto.setFechaComprobante(f.getFechaComprobante());
                    dto.setTipoComprobante(f.getTipoComprobante());
                    dto.setMontoTotal(f.getMontoTotal());
                    dto.setEstado(f.getEstado());
                    dto.setLetra(f.getLetra());
//                    dto.setPedidoDTO(f.getPedido().getIdPedido());
                    dto.setClienteDTO(f.getPedido().getCliente().getIdPersona());
                    // creo la lista vacia de MetodosDePAgoDTO
                    List<MetodoDePagoDTO> metodosPagoDTO = new ArrayList<>();

                    // Recorro la lista de facturas para añadir los mp a la nueva lista
                    for(FacturaMetodoPago mpFactura : f.getPagosRealizados() ){
                        MetodoDePagoDTO mpDTO = new MetodoDePagoDTO();
                        mpDTO.setId(mpFactura.getId());
                        mpDTO.setTipoMetodoPago(mpFactura.getMetodoDePago().getTipoMetodoPago().toString());
                        mpDTO.setMontoParcial(mpFactura.getMontoParcial());
                        metodosPagoDTO.add(mpDTO);
                    }
                    dto.setMetodosDePagoDTO(metodosPagoDTO);
                    return dto;
                })
                .toList();
        // Devolver la lista de facturasResponseDTO
        return facturasDTO;
    }

    @Override
    public void cambiarFinalizado(Long id) {
        Reparto reparto = this.findReparto(id);
            reparto.setEstado(EstadoReparto.FINALIZADO);
        repartoRepository.cambiarFinalizado(reparto.getIdReparto());
    }

    // Generar Hoja de Reparto
    @Override
    public RepartoResponseDTO saveReparto(RepartoRequestDTO repartoDTO) {
        Reparto reparto = new Reparto();

        // Busco los valores en base de datos y seteo cada atributo
        Empleado empleadoReparto = empleadoRepository.findById(repartoDTO.getIdRepartidor()).orElseThrow(
                () -> new RuntimeException("Empleado no encontrado"));
        reparto.setEmpleado(empleadoReparto);
        reparto.setFechaReparto(LocalDate.now());
        reparto.setEstado(EstadoReparto.PENDIENTE);

        // Recorro la lista de facturasDTO para agregar al reparto
        // y cambio el estado de la factura a ASIGNADA_A_REPARTO
        List<Factura> facturas = new ArrayList<>();
        for(Long idFacturaDTO : repartoDTO.getFacturasDTO()){
            Factura factura = facturaService.buscarPorNumeroComprobante(idFacturaDTO);
            facturaService.asignarAReparto(idFacturaDTO);
            facturas.add(factura);
        }
        reparto.setFacturas(facturas);
        // Guardo en la base de datos el reparto
        repartoRepository.save(reparto);

        //Creo el RepartoResponseDTO
        RepartoResponseDTO repartoResponseDTO = new RepartoResponseDTO();
        repartoResponseDTO.setIdReparto(reparto.getIdReparto());
        repartoResponseDTO.setEstado(reparto.getEstado());
        repartoResponseDTO.setFechaReparto(reparto.getFechaReparto());
        repartoResponseDTO.setIdRepartidor(reparto.getEmpleado().getIdPersona());
        repartoResponseDTO.setNombreRepartidor(reparto.getEmpleado().getNombre());
        repartoResponseDTO.setApellidoRepartidor(reparto.getEmpleado().getApellido());
        repartoResponseDTO.setZona(reparto.getEmpleado().getZona());
        // creo la lista de facturas para el response
        List<FacturaRepartoResponseDTO> facturasRespuesta = new ArrayList<>();

        // Recorrro la lista de facturas para setear los valores a cada fRepartoDTO
        for(Factura f : reparto.getFacturas()){
            FacturaRepartoResponseDTO fRepartoDTO = new FacturaRepartoResponseDTO();
            fRepartoDTO.setNumeroComprobante(f.getNumeroComprobante());
            fRepartoDTO.setFechaComprobante(f.getFechaComprobante());
            fRepartoDTO.setTipoComprobante(f.getTipoComprobante());
            fRepartoDTO.setLetra(f.getLetra());
            fRepartoDTO.setMontoTotal(f.getMontoTotal());
            fRepartoDTO.setNombreCliente(f.getPedido().getCliente().getNombre());
            fRepartoDTO.setApellidoCliente(f.getPedido().getCliente().getApellido());
            fRepartoDTO.setCalleCliente(f.getPedido().getCliente().getCalle());
            fRepartoDTO.setAlturaCliente(f.getPedido().getCliente().getAltura());
            facturasRespuesta.add(fRepartoDTO);
        }
        repartoResponseDTO.setFacturas(facturasRespuesta);
        return repartoResponseDTO;
    }

    // Eliminar la hoja de reparto y cambiar el estado de facturas a generadas
    @Transactional
    @Override
    public void deleteReparto(Long id) {
        Reparto reparto = repartoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Reparto no encontrado con el id: " + id));
        // Se crea la lista de facturas
        List<Factura> facturas = reparto.getFacturas();

        // Si hay facturas en la hoja, se actualizan y liberan
        if (facturas != null && !facturas.isEmpty()) {
            // Cambia estado
            facturas.forEach(f -> f.setEstado(EstadoFactura.GENERADA));
            // Guardar facturas con nuevo estado
            facturaRepository.saveAll(facturas);
            // Borra filas de la tabla intermedia reparto_facturas
            facturas.clear();
            // Guarda reparto sin facturas
            repartoRepository.save(reparto);
        }
        // Se elimina la hoja de reparto
        repartoRepository.delete(reparto);
    }
}
