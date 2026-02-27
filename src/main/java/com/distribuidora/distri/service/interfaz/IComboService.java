package com.distribuidora.distri.service.interfaz;

import com.distribuidora.distri.dto.request.ComboRequestDTO;
import com.distribuidora.distri.dto.response.ComboDTO;

import java.util.List;

public interface IComboService {
    List<ComboDTO> getCombos();

    ComboDTO findCombo(Long id);

    void saveCombo(ComboRequestDTO comboDTO);

    void deleteCombo(Long id);

    ComboDTO updateCombo(Long id, ComboRequestDTO comboDTO);
}
