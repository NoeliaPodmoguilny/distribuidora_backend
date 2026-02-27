package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.request.FacturaRequestDTO;
import com.distribuidora.distri.dto.response.FacturaResponseDTO;
import com.distribuidora.distri.dto.MetodoDePagoDTO;
import com.distribuidora.distri.enumm.*;
import com.distribuidora.distri.model.*;
import com.distribuidora.distri.repository.*;
import com.distribuidora.distri.service.interfaz.IFacturaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaService implements IFacturaService {

    @Autowired private IPedidoRepository pedidoRepository;
    @Autowired private ICuentaCorrienteRepository cuentaCorrienteRepository;
    @Autowired private IMetodoDePagoRepository metodoDePagoRepository;
    @Autowired private IFacturaRepository facturaRepository;
    @Autowired private IFacturaMetodoPagoRepository facturaMetodoPagoRepository;

    //Obtener todas las facturas existentes
    @Override
    public List<FacturaResponseDTO> getFacturas() {
        List<Factura> listaFacturas = facturaRepository.findAll();
        List<FacturaMetodoPago> listaFacturasMetodosPagos = facturaMetodoPagoRepository.findAll();

        return listaFacturas.stream()
                .map(factura -> {
                    FacturaResponseDTO dto = new FacturaResponseDTO();

                    // Datos base de la factura
                    dto.setNumeroComprobante(factura.getNumeroComprobante());
                    dto.setFechaComprobante(factura.getFechaComprobante());
                    dto.setTipoComprobante(factura.getTipoComprobante());
                    dto.setMontoTotal(factura.getMontoTotal());
                    dto.setLetra(factura.getLetra());
                    dto.setEstado(factura.getEstado());
                    dto.setClienteDTO(factura.getPedido().getCliente().getIdPersona());

                    // Filtrar los métodos de pago asociados a esta factura
                    List<MetodoDePagoDTO> metodosPagoDTO = listaFacturasMetodosPagos.stream()
                            .filter(fmp -> fmp.getFactura().getNumeroComprobante().equals(factura.getNumeroComprobante()))
                            .map(fmp -> {
                                MetodoDePagoDTO mpDTO = new MetodoDePagoDTO();
                                mpDTO.setId(fmp.getMetodoDePago().getIdMetodoPago());
                                mpDTO.setTipoMetodoPago(String.valueOf(fmp.getMetodoDePago().getTipoMetodoPago()));
                                mpDTO.setMontoParcial(fmp.getMontoParcial());
                                return mpDTO;
                            })
                            .collect(Collectors.toList());

                    dto.setMetodosDePagoDTO(metodosPagoDTO);

                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Buscar factura por numero de comprobante
    public Factura buscarPorNumeroComprobante(Long numeroComprobante){
        return facturaRepository.findFacturaByNumeroComprobante(numeroComprobante);
    }

    // Obtener factura por id
    @Override
    public FacturaResponseDTO findFactura(Long id) {
        Factura factura = facturaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Factura no encontrada"));

        List<FacturaMetodoPago> listaFacturasMetodosPagos = facturaMetodoPagoRepository.findAll();
        FacturaResponseDTO dto = new FacturaResponseDTO();

        // Datos base de la factura
        dto.setNumeroComprobante(factura.getNumeroComprobante());
        dto.setFechaComprobante(factura.getFechaComprobante());
        dto.setTipoComprobante(factura.getTipoComprobante());
        dto.setMontoTotal(factura.getMontoTotal());
        dto.setLetra(factura.getLetra());
        dto.setEstado(factura.getEstado());
        dto.setClienteDTO(factura.getPedido().getCliente().getIdPersona());

        // Filtrar los métodos de pago asociados a esta factura
        List<MetodoDePagoDTO> metodosPagoDTO = listaFacturasMetodosPagos.stream()
                .filter(fmp -> fmp.getFactura().getNumeroComprobante().equals(factura.getNumeroComprobante()))
                .map(fmp -> {
                    MetodoDePagoDTO mpDTO = new MetodoDePagoDTO();
                    mpDTO.setId(fmp.getMetodoDePago().getIdMetodoPago());
                    mpDTO.setMontoParcial(fmp.getMontoParcial());
                    mpDTO.setTipoMetodoPago(fmp.getMetodoDePago().getTipoMetodoPago().name());
                    return mpDTO;
                })
                .collect(Collectors.toList());
        dto.setMetodosDePagoDTO(metodosPagoDTO);
        return dto;
    }


    // GENERAR FACTURA PARA UN PEDIDO
    @Override
    @Transactional
    public FacturaResponseDTO generarFactura(FacturaRequestDTO facturaDTO) {

        // OBTENER DATOS DEL PEDIDO
        Pedido pedido = pedidoRepository.findById(facturaDTO.getPedidoDTO())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + facturaDTO.getPedidoDTO()));
        pedido.setEstado(Estado.FACTURADO);
        Cliente cliente = pedido.getCliente();
        Double montoTotalPedido = pedido.getMontoTotalPedido();

        Double montoACuentaCorriente = 0.0;

        // VALIDAR PAGOS Y CALCULAR CRÉDITO

        // Sumar todos los montos parciales del DTO
        Double sumaPagos = facturaDTO.getMetodosDePagoDTO().stream()
                .mapToDouble(MetodoDePagoDTO::getMontoParcial)
                .sum();

        // El total pagado debe igualar el total del pedido
        if (!montoTotalPedido.equals(sumaPagos)) {
            throw new RuntimeException("Error: La suma de los pagos (" + sumaPagos + ") no coincide con el monto total del pedido (" + montoTotalPedido + ")");
        }

        // CREAR ENTIDAD FACTURA Y GUARDAR EN LA BD
        Factura factura = new Factura();

        // Determinar Y asignar letra segun el cliente
        Letra letraFactura = determinarLetra(cliente.getCategoriaCliente());

        // Atributos generales del Comprobante
        factura.setTipoComprobante(TipoComprobante.FACTURA);
        factura.setFechaComprobante(LocalDate.now());
        factura.setMontoTotal(montoTotalPedido);

        // Atributos generales de la Factura
        factura.setEstado(EstadoFactura.GENERADA);
        factura.setLetra(letraFactura);
        factura.setPedido(pedido);

        // Guardar la factura primero para obtener su ID
        Factura facturaGuardada = facturaRepository.save(factura);

        // CREAR Y PERSISTIR REGISTROS DE LA TABLA INTERMEDIA (FacturaMetodoPago)
        List<FacturaMetodoPago> pagosRealizados = new ArrayList<>();

        for (MetodoDePagoDTO mpDTO : facturaDTO.getMetodosDePagoDTO()) {

            MetodoDePago mpEntidad = metodoDePagoRepository.findById(mpDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Método de pago no encontrado con ID: " + mpDTO.getId()));

            // Crear el registro de la tabla intermedia (FacturaMetodoPago)
            FacturaMetodoPago registroPago = new FacturaMetodoPago();
            registroPago.setFactura(facturaGuardada);
            registroPago.setMetodoDePago(mpEntidad);
            registroPago.setMontoParcial(mpDTO.getMontoParcial());

            // Acumular monto para Cuenta Corriente si el tipo es CUENTA_CORRIENTE
            if (mpEntidad.getTipoMetodoPago().name().equals("CUENTA_CORRIENTE")) {
                montoACuentaCorriente += mpDTO.getMontoParcial();
            }
            pagosRealizados.add(registroPago);
        }

        // Guardar todos los registros de pago intermedios en la BD
        facturaMetodoPagoRepository.saveAll(pagosRealizados);

        // ACTUALIZAR CUENTA CORRIENTE
        // Solo se llama si hubo algún monto a CUENTA_CORRIENTE
        if (montoACuentaCorriente > 0.0) {
            actualizarCuentaCorriente(cliente, montoACuentaCorriente);
        }

        FacturaResponseDTO facturaResponseDTO = new FacturaResponseDTO();
        // RETORNAR DTO ACTUALIZADO
        facturaResponseDTO.setNumeroComprobante(facturaGuardada.getNumeroComprobante());
        facturaResponseDTO.setFechaComprobante(facturaGuardada.getFechaComprobante());
        facturaResponseDTO.setMontoTotal(facturaGuardada.getMontoTotal());
        facturaResponseDTO.setTipoComprobante(facturaGuardada.getTipoComprobante());
        facturaResponseDTO.setLetra(facturaGuardada.getLetra());
        facturaResponseDTO.setEstado(facturaGuardada.getEstado());
        facturaResponseDTO.setMetodosDePagoDTO(facturaDTO.getMetodosDePagoDTO());
        facturaResponseDTO.setClienteDTO(factura.getPedido().getCliente().getIdPersona());

        return facturaResponseDTO;
    }

    @Override
    @Transactional
    public List<FacturaResponseDTO> generarFacturaMasiva(List<FacturaRequestDTO> facturasDTO) {
        return facturasDTO.stream()
                .map(this::generarFactura)
                .collect(Collectors.toList());
    }

    /**
     * - MÉTODOS AUXILIARES PARA GENERAR FACTURA
     */
    private Letra determinarLetra(CategoriaCliente categoriaCliente) {
        if (categoriaCliente == CategoriaCliente.RI ||
                categoriaCliente == CategoriaCliente.MT ||
                categoriaCliente == CategoriaCliente.EXC) {
            return Letra.A;
        } else {
            return Letra.B;
        }
    }

    /**
     * Actualiza la cuenta corriente del cliente sólo con el monto específico
     */
    private void actualizarCuentaCorriente(Cliente cliente, Double montoACuentaCorriente) {
        // Buscar Cuenta Corriente existente por cliente
        CuentaCorriente cc = cuentaCorrienteRepository.findByClienteIdPersona(cliente.getIdPersona())
                .orElse(null);

        if (cc == null) {
            // Crear nueva cuenta si es la primera vez
            cc = new CuentaCorriente();
            cc.setCliente(cliente);
            cc.setSaldoActual(montoACuentaCorriente);
            cc.setFechaUltimaTransaccion(LocalDate.now());
            cc.setCondicion("30 dias");
        } else {
            // Sumar sólo el monto a cuenta_corriente al saldo existente
            cc.setSaldoActual(cc.getSaldoActual() + montoACuentaCorriente);
            cc.setFechaUltimaTransaccion(LocalDate.now());
        }
        cuentaCorrienteRepository.save(cc);
    }
    // Cambia el estado para asignarla al reparto
    public void asignarAReparto(Long idFacturaDTO) {
        Factura f = facturaRepository.findById(idFacturaDTO).orElseThrow(()-> new RuntimeException("Factura no encontrada"));
        f.setEstado(EstadoFactura.ASIGNADA_A_REPARTO);
    }
}

