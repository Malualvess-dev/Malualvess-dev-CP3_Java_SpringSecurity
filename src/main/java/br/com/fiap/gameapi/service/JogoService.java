package br.com.fiap.gameapi.service;

import br.com.fiap.gameapi.dto.Request.JogoRequest;
import br.com.fiap.gameapi.dto.Response.JogoResponse;
import br.com.fiap.gameapi.dto.Response.MensagemResponse;
import br.com.fiap.gameapi.exception.ResourceNotFoundException;
import br.com.fiap.gameapi.mapper.JogoMapper;
import br.com.fiap.gameapi.model.Jogo;
import br.com.fiap.gameapi.model.Plataforma;
import br.com.fiap.gameapi.repository.JogoRepository;
import br.com.fiap.gameapi.repository.PlataformaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;
    private final PlataformaRepository plataformaRepository;
    private final JogoMapper mapper;

    public JogoService(
            JogoRepository jogoRepository,
            PlataformaRepository plataformaRepository,
            JogoMapper mapper
    ) {
        this.jogoRepository = jogoRepository;
        this.plataformaRepository = plataformaRepository;
        this.mapper = mapper;
    }

    public List<JogoResponse> listar() {
        return jogoRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public JogoResponse buscarPorId(Long id) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado."));

        return mapper.toResponse(jogo);
    }

    public MensagemResponse criar(JogoRequest request) {
        Plataforma plataforma = plataformaRepository.findById(request.plataformaId())
                .orElseThrow(() -> new ResourceNotFoundException("Plataforma não encontrada."));

        Jogo jogo = mapper.toEntity(request, plataforma);
        Jogo salvo = jogoRepository.save(jogo);

        return new MensagemResponse(
                "Jogo criado com sucesso.",
                mapper.toResponse(salvo)
        );
    }

    public MensagemResponse atualizar(Long id, JogoRequest request) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado."));

        Plataforma plataforma = plataformaRepository.findById(request.plataformaId())
                .orElseThrow(() -> new ResourceNotFoundException("Plataforma não encontrada."));

        jogo.setTitulo(request.titulo());
        jogo.setGenero(request.genero());
        jogo.setAnoLancamento(request.anoLancamento());
        jogo.setPreco(request.preco());
        jogo.setPlataforma(plataforma);

        Jogo atualizado = jogoRepository.save(jogo);

        return new MensagemResponse(
                "Jogo atualizado com sucesso.",
                mapper.toResponse(atualizado)
        );
    }

    public MensagemResponse deletar(Long id) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado."));

        jogoRepository.delete(jogo);

        return new MensagemResponse(
                "Jogo removido com sucesso.",
                null
        );
    }
}