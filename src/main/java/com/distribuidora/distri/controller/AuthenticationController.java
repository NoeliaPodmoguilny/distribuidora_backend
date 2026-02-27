package com.distribuidora.distri.controller;

import com.distribuidora.distri.dto.authentication.AuthLoginRequestDTO;
import com.distribuidora.distri.dto.authentication.AuthResponseDTO;
import com.distribuidora.distri.service.UserDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthLoginRequestDTO userRequest) {
        AuthResponseDTO response = userDetailsService.loginUser(userRequest);
        return ResponseEntity.ok(response);
    }
}
