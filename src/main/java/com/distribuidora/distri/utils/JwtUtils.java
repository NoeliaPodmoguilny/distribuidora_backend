package com.distribuidora.distri.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    @Value("${security.jwt.private.key}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    //Metodo para encriptar, usamos clave secreta y algoritmo
    public String createToken(Authentication authentication) {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

        // getPrincipal representa el usuario autenticado
        String username = authentication.getPrincipal().toString();

        //obtenemos los permisos
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        //generamos el token
        String jwtToken = JWT.create()
                .withIssuer(this.userGenerator) //organizacion que genera el token
                .withSubject(username) // uauario a quien se le genera el token
                .withClaim("authorities", authorities) //permisos
                .withIssuedAt(new Date()) //fecha de generación del token
                .withExpiresAt(new Date(System.currentTimeMillis() + 28800000)) //fecha de expiración (8 horas), tiempo en milisegundos (60' x 60.000ms)
                .withJWTId(UUID.randomUUID().toString()) //id random para el token
                .withNotBefore(new Date(System.currentTimeMillis())) //el token es válido desde este moemnto
                .sign(algorithm); // firma creada con la clave secreta
        return jwtToken;
    }

    //funcion para decodificar token
    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey); //algoritmo + clave privada
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();
            //si está ok, no genera excepción y hace el return
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("NO AUTORIZADO. Token inválido");
        }
    }

    //Metodo para obtener username
    public String extractUsername(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject().toString();
    }

    //devuelvo un claim en particular
    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }

    //devuelvo todos los claims
    public Map<String, Claim> returnAllClaims(DecodedJWT decodedJWT) {
        return decodedJWT.getClaims();
    }

}