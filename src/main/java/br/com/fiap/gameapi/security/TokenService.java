package br.com.fiap.gameapi.security;

import br.com.fiap.gameapi.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final String SECRET_KEY = "gameapi-secret";

    public String gerarToken(Usuario usuario) {

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return JWT.create()
                .withIssuer("gameapi")
                .withSubject(usuario.getLogin())
                .withClaim("role", usuario.getRole())
                .withExpiresAt(dataExpiracao())
                .sign(algorithm);
    }

    public String validarToken(String token) {

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return JWT.require(algorithm)
                .withIssuer("gameapi")
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant dataExpiracao() {

        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}