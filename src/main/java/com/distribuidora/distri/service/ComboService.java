package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.request.ComboRequestDTO;
import com.distribuidora.distri.dto.response.ComboDTO;

import com.distribuidora.distri.dto.response.ComboProductoDTO;
import com.distribuidora.distri.model.Combo;
import com.distribuidora.distri.model.ComboProducto;
import com.distribuidora.distri.model.Producto;
import com.distribuidora.distri.repository.IComboProductoRepository;
import com.distribuidora.distri.repository.IComboRepository;
import com.distribuidora.distri.repository.IProductoRepository;
import com.distribuidora.distri.service.interfaz.IComboService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComboService implements IComboService {

    @Autowired private IComboRepository comboRepository;
    @Autowired private IComboProductoRepository comboProductoRepository;
    @Autowired private IProductoRepository productoRepository;

    @Override
    public List<ComboDTO> getCombos() {
        return comboRepository.findAll()
                .stream()
                .map(combo -> new ComboDTO(
                        combo.getCodigoCombo(),
                        combo.getDescripcion(),
                        combo.getFechaAlta(),
                        combo.getFechaBaja(),
                        combo.getPorcentajeDescuento(),
                        combo.getComboProductos()
                                .stream()
                                .map(this::mapComboProductoToDTO)
                                .toList()
                ))
                .toList();
    }
    // AUXILIAR para mapear la lista de comboproducto
    private ComboProductoDTO mapComboProductoToDTO(ComboProducto cp) {
        return new ComboProductoDTO(
                cp.getIdComboProducto(),
                cp.getProducto().getIdProducto(),
                cp.getCombo().getCodigoCombo(),
                cp.getPorcentajeDescuento(),
                cp.getCantidad()
        );
    }

    @Override
    public ComboDTO findCombo(Long id) {
        Combo combo = comboRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Combo no encontrado"));
        return new ComboDTO(
                combo.getCodigoCombo(),
                combo.getDescripcion(),
                combo.getFechaAlta(),
                combo.getFechaBaja(),
                combo.getPorcentajeDescuento(),
                combo.getComboProductos()
                        .stream()
                        .map(this::mapComboProductoToDTO)
                        .toList());
    }

    @Override
    @Transactional
    public void saveCombo(ComboRequestDTO comboDTO) {

        Combo combo = new Combo();
        combo.setDescripcion(comboDTO.getDescripcion());
        combo.setFechaAlta(comboDTO.getFechaAlta());
        combo.setFechaBaja(comboDTO.getFechaBaja());
        combo.setPorcentajeDescuento(comboDTO.getPorcentajeDescuento());

        List<ComboProducto> comboProductos = comboDTO.getComboProductosDTO()
                .stream()
                .map(dto -> {
                    Producto producto = productoRepository.findById(dto.getProductoDTO())
                            .orElseThrow(() ->
                                    new RuntimeException("Producto no encontrado con id: " + dto.getProductoDTO()));

                    ComboProducto cp = new ComboProducto();
                    cp.setProducto(producto);
                    cp.setCombo(combo);
                    cp.setCantidad(dto.getCantidad());
                    cp.setPorcentajeDescuento(dto.getPorcentajeDescuento());
                    return cp;
                })
                .toList();
        combo.setComboProductos(comboProductos);
        comboRepository.save(combo);
    }

    @Override
    @Transactional
    public void deleteCombo(Long id) {
        Combo combo = comboRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Combo no encontrado con id: " + id));
        comboRepository.delete(combo);
    }

    @Override
    @Transactional
    public ComboDTO updateCombo(Long id, ComboRequestDTO comboDTO) {

        Combo combo = comboRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Combo no encontrado con id: " + id));
        combo.setDescripcion(comboDTO.getDescripcion());
        combo.setFechaAlta(comboDTO.getFechaAlta());
        combo.setFechaBaja(comboDTO.getFechaBaja());
        combo.setPorcentajeDescuento(comboDTO.getPorcentajeDescuento());

        //Limpio relaciones existentes
        combo.getComboProductos().clear();
        // Reconstruyo la lista desde el DTO
        comboDTO.getComboProductosDTO().forEach(dto -> {
            Producto producto = productoRepository.findById(dto.getProductoDTO())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + dto.getProductoDTO()));

            ComboProducto cp = new ComboProducto();
            cp.setCombo(combo);
            cp.setProducto(producto);
            cp.setCantidad(dto.getCantidad());
            cp.setPorcentajeDescuento(dto.getPorcentajeDescuento());
            combo.getComboProductos().add(cp);
        });
        Combo comboActualizado = comboRepository.save(combo);
        //Devuelvo DTO actualizado
        return new ComboDTO(
                comboActualizado.getCodigoCombo(),
                comboActualizado.getDescripcion(),
                comboActualizado.getFechaAlta(),
                comboActualizado.getFechaBaja(),
                comboActualizado.getPorcentajeDescuento(),
                comboActualizado.getComboProductos()
                        .stream()
                        .map(cp -> new ComboProductoDTO(
                                cp.getIdComboProducto(),
                                cp.getProducto().getIdProducto(),
                                cp.getCombo().getCodigoCombo(),
                                cp.getPorcentajeDescuento(),
                                cp.getCantidad()
                        ))
                        .toList()
        );
    }
}
