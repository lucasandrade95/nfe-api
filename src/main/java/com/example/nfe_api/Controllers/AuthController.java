package com.example.nfe_api.Controllers;

import com.example.nfe_api.DTO.LoginDTO;
import com.example.nfe_api.DTO.LogoutRequestDTO;
import com.example.nfe_api.DTO.RefreshTokenRequestDTO;
import com.example.nfe_api.DTO.RegisterDTO;
import com.example.nfe_api.Entitys.Usuario;
import com.example.nfe_api.Repositorys.UsuarioRepository;
import com.example.nfe_api.Security.JwtUtil;
import com.example.nfe_api.Service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO dto) {
        if (usuarioRepo.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já em uso.");
        }

        Usuario u = new Usuario(null, dto.getEmail(), encoder.encode(dto.getSenha()));
        usuarioRepo.save(u);
        return ResponseEntity.ok("Usuário registrado com sucesso.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO dto) {
        Usuario u = usuarioRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!encoder.matches(dto.getSenha(), u.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }

        String accessToken = jwtUtil.generateToken(u);
        String refreshToken = refreshService.createRefreshToken(u);

        return ResponseEntity.ok(Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        ));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody @Valid RefreshTokenRequestDTO req) {
        String refreshToken = req.getRefreshToken();
        return ResponseEntity.ok(refreshService.refreshAccessToken(refreshToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody @Valid LogoutRequestDTO req) {
        refreshService.invalidateRefreshToken(req.getRefreshToken());
        return ResponseEntity.ok("Logout realizado.");
    }
}