package br.com.fiap.gameapi.service;

import br.com.fiap.gameapi.dto.Request.PlataformaRequest;
import br.com.fiap.gameapi.dto.Response.MensagemResponse;
import br.com.fiap.gameapi.dto.Response.PlataformaResponse;
import br.com.fiap.gameapi.exception.ResourceNotFoundException;
import br.com.fiap.gameapi.mapper.PlataformaMapper;
import br.com.fiap.gameapi.model.Plataforma;
import br.com.fiap.gameapi.repository.PlataformaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlataformaService {

    private final PlataformaRepository repository;
    private final PlataformaMapper mapper;

    public PlataformaService(PlataformaRepository repository, PlataformaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PlataformaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public PlataformaResponse buscarPorId(Long id) {
        Plataforma plataforma = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plataforma não encontrada."));

        return mapper.toResponse(plataforma);
    }

    public MensagemResponse criar(PlataformaRequest request) {
        Plataforma plataforma = mapper.toEntity(request);
        Plataforma salvo = repository.save(plataforma);

        return new MensagemResponse(
                "Plataforma criada com sucesso.",
                mapper.toResponse(salvo)
        );
    }

    public MensagemResponse atualizar(Long id, PlataformaRequest request) {
        Plataforma plataforma = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plataforma não encontrada."));

        plataforma.setNome(request.nome());
        plataforma.setFabricante(request.fabricante());

        Plataforma atualizado = repository.save(plataforma);

        return new MensagemResponse(
                "Plataforma atualizada com sucesso.",
                mapper.toResponse(atualizado)
        );
    }

    public MensagemResponse deletar(Long id) {
        Plataforma plataforma = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plataforma não encontrada."));

        repository.delete(plataforma);

        return new MensagemResponse(
                "Plataforma removida com sucesso.",
                null
        );
    }
}