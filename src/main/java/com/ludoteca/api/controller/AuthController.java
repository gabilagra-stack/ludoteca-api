package com.ludoteca.api.controller;


import com.ludoteca.api.dto.request.LoginRequestDto;
import com.ludoteca.api.dto.response.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> login(@RequestBody final LoginRequestDto request) {
        UsuarioDto usuarioAutenticado = usuarioService.login(request);
        return ResponseEntity.ok(usuarioAutenticado);
    }
}
