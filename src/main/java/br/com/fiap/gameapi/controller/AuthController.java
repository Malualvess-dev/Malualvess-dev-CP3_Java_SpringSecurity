package br.com.fiap.gameapi.controller;

import br.com.fiap.gameapi.dto.Request.LoginRequest;
import br.com.fiap.gameapi.dto.Request.UsuarioRequest;
import br.com.fiap.gameapi.dto.Response.MensagemResponse;
import br.com.fiap.gameapi.dto.Response.TokenResponse;
import br.com.fiap.gameapi.model.Usuario;
import br.com.fiap.gameapi.repository.UsuarioRepository;
import br.com.fiap.gameapi.security.TokenService;
import br.com.fiap.gameapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            AuthenticationManager authenticationManager,
            TokenService tokenService,
            UsuarioRepository usuarioRepository,
            UsuarioService usuarioService,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(
            @RequestBody @Valid LoginRequest request
    ) {

        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        request.login(),
                        request.senha()
                );

        authenticationManager.authenticate(usernamePassword);

        Usuario usuario = usuarioRepository.findByLogin(request.login())
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado")
                );

        String token = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(
                new TokenResponse(token)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<MensagemResponse> register(
            @RequestBody @Valid UsuarioRequest request
    ) {

        if (usuarioRepository.findByLogin(request.login()).isPresent()) {

            return ResponseEntity.badRequest().body(
                    new MensagemResponse(
                            "Usuário já cadastrado.",
                            null
                    )
            );
        }

        Usuario usuario = new Usuario();

        usuario.setLogin(request.login());

        usuario.setSenha(
                passwordEncoder.encode(request.senha())
        );

        usuario.setRole(request.role());

        usuarioRepository.save(usuario);

        return ResponseEntity.ok(
                new MensagemResponse(
                        "Usuário cadastrado com sucesso.",
                        null
                )
        );
    }
}