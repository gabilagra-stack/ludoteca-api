package com.ludoteca.api.controller;


import com.ludoteca.api.dto.request.LoginRequestDto;
import com.ludoteca.api.dto.response.LoginResponseDto;
import com.ludoteca.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody final LoginRequestDto request) {
        LoginResponseDto usuarioAutenticado = authService.login(request);
        return ResponseEntity.ok(usuarioAutenticado);
    }
}
