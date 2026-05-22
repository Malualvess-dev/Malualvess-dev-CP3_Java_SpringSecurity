package br.com.fiap.gameapi.mapper;

import br.com.fiap.gameapi.dto.Request.JogoRequest;
import br.com.fiap.gameapi.dto.Response.JogoResponse;
import br.com.fiap.gameapi.dto.Response.PlataformaResponse;
import br.com.fiap.gameapi.model.Jogo;
import br.com.fiap.gameapi.model.Plataforma;
import org.springframework.stereotype.Component;

@Component
public class JogoMapper {

    public Jogo toEntity(JogoRequest request, Plataforma plataforma) {
        Jogo jogo = new Jogo();

        jogo.setTitulo(request.titulo());
        jogo.setGenero(request.genero());
        jogo.setAnoLancamento(request.anoLancamento());
        jogo.setPreco(request.preco());
        jogo.setPlataforma(plataforma);

        return jogo;
    }

    public JogoResponse toResponse(Jogo jogo) {
        PlataformaResponse plataformaResponse = new PlataformaResponse(
                jogo.getPlataforma().getId(),
                jogo.getPlataforma().getNome(),
                jogo.getPlataforma().getFabricante()
        );

        return new JogoResponse(
                jogo.getId(),
                jogo.getTitulo(),
                jogo.getGenero(),
                jogo.getAnoLancamento(),
                jogo.getPreco(),
                plataformaResponse
        );
    }
}