package br.com.fiap.gameapi.mapper;

import br.com.fiap.gameapi.dto.Request.UsuarioRequest;
import br.com.fiap.gameapi.dto.Response.UsuarioResponse;
import br.com.fiap.gameapi.model.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    private final PasswordEncoder passwordEncoder;

    public UsuarioMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario toEntity(UsuarioRequest request) {
        Usuario usuario = new Usuario();

        usuario.setLogin(request.login());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuario.setRole(request.role());

        return usuario;
    }

    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getRole()
        );
    }
}