package com.ludoteca.api.utils;

import com.ludoteca.api.enums.RolUsuario;
import com.ludoteca.api.model.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration}")
    private long expirationInMs;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generarToken(Usuario usuario) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationInMs);

        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("rol", usuario.getRol())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication obtenerAutenticacion(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String email = claims.getSubject();
        String rol = claims.get("rol", String.class);
        RolUsuario rolUsuario = RolUsuario.valueOf(rol);

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setRol(rolUsuario);
        UsuarioPrincipal usuarioPrincipal = new UsuarioPrincipal(usuario);

        return new UsernamePasswordAuthenticationToken(usuarioPrincipal, token, usuarioPrincipal.getAuthorities());
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            log.warn("Token expirado: {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.warn("Token no soportado: {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.warn("Token mal formado: {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.warn("Token vacío o nulo: {}", ex.getMessage());
        }
        return false;
    }

    public String obtenerEmailDesdeToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
