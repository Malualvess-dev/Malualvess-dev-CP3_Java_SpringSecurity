package br.com.fiap.gameapi.service;

import br.com.fiap.gameapi.dto.Request.UsuarioRequest;
import br.com.fiap.gameapi.dto.Response.MensagemResponse;
import br.com.fiap.gameapi.dto.Response.UsuarioResponse;
import br.com.fiap.gameapi.exception.ResourceNotFoundException;
import br.com.fiap.gameapi.mapper.UsuarioMapper;
import br.com.fiap.gameapi.model.Usuario;
import br.com.fiap.gameapi.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository repository,
            UsuarioMapper mapper,
            PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));

        return mapper.toResponse(usuario);
    }

    public MensagemResponse criar(UsuarioRequest request) {
        Usuario usuario = mapper.toEntity(request);
        Usuario salvo = repository.save(usuario);

        return new MensagemResponse(
                "Usuário criado com sucesso.",
                mapper.toResponse(salvo)
        );
    }

    public MensagemResponse atualizar(Long id, UsuarioRequest request) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));

        usuario.setLogin(request.login());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuario.setRole(request.role());

        Usuario atualizado = repository.save(usuario);

        return new MensagemResponse(
                "Usuário atualizado com sucesso.",
                mapper.toResponse(atualizado)
        );
    }

    public MensagemResponse deletar(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));

        repository.delete(usuario);

        return new MensagemResponse(
                "Usuário removido com sucesso.",
                null
        );
    }
}