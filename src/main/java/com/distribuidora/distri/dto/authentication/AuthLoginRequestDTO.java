package com.distribuidora.distri.dto.authentication;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDTO(
        @NotBlank(message = "El nombre de usuario no puede estar vacío")
        String username,
        @NotBlank(message = "La contraseña no puede estar vacía")
        String password) {

}
