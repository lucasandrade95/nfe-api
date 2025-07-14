package com.example.nfe_api.Service;

import com.example.nfe_api.Entitys.RefreshToken;
import com.example.nfe_api.Entitys.Usuario;
import com.example.nfe_api.Repositorys.RefreshTokenRepository;
import com.example.nfe_api.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repo;
    private final JwtUtil jwtUtil;

    public String createRefreshToken(Usuario usuario) {
        String token = UUID.randomUUID().toString();
        RefreshToken rt = new RefreshToken(null, usuario, token, LocalDateTime.now().plusDays(1));
        repo.save(rt);
        return token;
    }

    public Map<String, String> refreshAccessToken(String token) {
        RefreshToken rt = repo.findByToken(token).orElseThrow(() -> new RuntimeException("Token inválido"));
        if (rt.getExpiracao().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }
        String accessToken = jwtUtil.generateToken(rt.getUsuario());
        return Map.of("accessToken", accessToken);
    }

    public void invalidateRefreshToken(String token) {
        repo.findByToken(token).ifPresent(repo::delete);
    }
}
