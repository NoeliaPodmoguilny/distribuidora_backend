package com.distribuidora.distri.service;

import com.distribuidora.distri.dto.CategoriaProductoDTO;
import com.distribuidora.distri.model.CategoriaProducto;
import com.distribuidora.distri.repository.ICategoriaRepository;
import com.distribuidora.distri.service.interfaz.ICategoriaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static com.distribuidora.distri.helper.UpdateHelper.setIf;
import static com.distribuidora.distri.helper.UpdateHelper.setIfNotNull;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaProducto> getCategoriaProductos() {
        return categoriaRepository.findAll();
    }

    @Override
    public CategoriaProducto findCategoriaProducto(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
    }

    @Override
    public CategoriaProducto findCategoriaProductoBynombreCategoriaProducto(String nombreCategoriaProducto) {
        CategoriaProducto categoria = categoriaRepository.findCategoriaProductoByNombreCategoria(nombreCategoriaProducto);
        if (categoria == null) {
            throw new NoSuchElementException("No se encontró la categoría con el nombre: " + nombreCategoriaProducto);
        }
        return categoria;
    }

    @Override
    public CategoriaProducto saveCategoriaProducto(CategoriaProductoDTO categoriaProductoDTO) {
        CategoriaProducto categoriaProd = new CategoriaProducto();
        setIf(categoriaProductoDTO.getNombreCategoria(),
                nombre -> nombre.length() < 20,
                categoriaProd::setNombreCategoria);
        setIfNotNull(categoriaProductoDTO.getDescripcionCategoria(), categoriaProd::setDescripcionCategoria);
        return categoriaRepository.save(categoriaProd);
    }

    @Override
    public void deleteCategoriaProducto(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria no encontrada con el id: " + id);
        }
        categoriaRepository.deleteById(id);
    }

    @Override
    public CategoriaProducto updateCategoriaProducto(Long id, CategoriaProductoDTO categoriaProductoDTO) {
        CategoriaProducto categoriaProd = this.findCategoriaProducto(id);

        // Actualiza sólo los atributos que cumplan las condiciones
        setIf(categoriaProductoDTO.getNombreCategoria(),
                nombre -> nombre.length() < 20,
                categoriaProd::setNombreCategoria);
        setIfNotNull(categoriaProductoDTO.getDescripcionCategoria(), categoriaProd::setDescripcionCategoria);

        return categoriaRepository.save(categoriaProd);
    }
}
