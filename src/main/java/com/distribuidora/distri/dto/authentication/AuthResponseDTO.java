package com.distribuidora.distri.dto.authentication;

import com.distribuidora.distri.model.Permiso;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;


@JsonPropertyOrder({"username","permisos", "idempleado", "message", "jwt", "status"})
public record AuthResponseDTO(String username, List<Permiso> permisos, Long idempleado, String message, String jwt, boolean status) {

}

