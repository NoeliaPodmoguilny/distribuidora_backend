package com.distribuidora.distri.service;

import java.util.ArrayList;
import java.util.List;

import com.distribuidora.distri.dto.authentication.AuthLoginRequestDTO;
import com.distribuidora.distri.dto.authentication.AuthResponseDTO;
import com.distribuidora.distri.model.Empleado;
import com.distribuidora.distri.model.Permiso;
import com.distribuidora.distri.repository.IEmpleadoRepository;
import com.distribuidora.distri.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired private IEmpleadoRepository userRepo;
    @Autowired private JwtUtils jwtUtils;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private IEmpleadoRepository empleadoRepository;

    // TENEMOS EL USUARIO Y DEVOLVEMOS UN USERDETAILS
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Traemos el usuario de la base de datos usando el username
        Empleado usuario = userRepo.findEmpleadoEntityByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario  " + username + " no fue encontrado"));
        // Si el usuario esta inactivo no puede ingresar
        if (!usuario.getActivo()) {
            throw new DisabledException("El usuario está deshabilitado. Consulte con su superior");
        }
        // Creamos una lista de GrantedAuthority para el rol del usuario
        // CON ESTA CLASE SPRING SECURITY MANEJA LOS PERMISOS
        List<GrantedAuthority> authorityList = new ArrayList<>();

        // TOMAMOS LA POSICION Y LO CONVERTIRMOS A  SimpleGrantedAuthority (con el prefijo "ROLE_" que usa Spring Security) Y LO AGREGAMOS A LA LISTA
        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(usuario.getPosicion().toString())));

        // También agregamos a la lista los permisos asociados al rol
        usuario.getPermisos().forEach(permiso
                -> authorityList.add(new SimpleGrantedAuthority(permiso.getDescripcionPermiso()))
        );
        User usu = new User(usuario.getUsuario(),
                usuario.getContraseña(),
                authorityList);
        // Retornamos el USER en formato Spring Security con los datos del usuario
        return usu ;
    }

    //Metodo loginUser
    public AuthResponseDTO loginUser(AuthLoginRequestDTO authLoginRequest) {

        //recuperamos nombre de usuario y contraseña
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Empleado emple = empleadoRepository.findEmpleadoEntityByUsuario(username).orElseThrow();

        Authentication authentication = this.authenticate(username, password);

        //si está ok, obtenemos el SecurityContext y creamos el token
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        List<Permiso> permisos = new ArrayList<>();
        for(Permiso permiso : emple.getPermisos()){
            permisos.add(permiso);
        }
        Long idempleado = emple.getIdPersona();
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(username, permisos, idempleado, "login ok", accessToken, true);
        return authResponseDTO;
    }

    //Metodo authenticate()
    public Authentication authenticate(String username, String password) {
        //buscamos el usuario
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Contraseña y/o usuario incorrectos");
        }
        // manejo de error, si el pass no es igual
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Contraseña y/o usuario incorrectos");
        }
        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }
}