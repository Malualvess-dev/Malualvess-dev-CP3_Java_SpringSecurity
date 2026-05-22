package br.com.fiap.gameapi.mapper;

import br.com.fiap.gameapi.dto.Request.PlataformaRequest;
import br.com.fiap.gameapi.dto.Response.PlataformaResponse;
import br.com.fiap.gameapi.model.Plataforma;
import org.springframework.stereotype.Component;

@Component
public class PlataformaMapper {

    public Plataforma toEntity(PlataformaRequest request) {
        Plataforma plataforma = new Plataforma();

        plataforma.setNome(request.nome());
        plataforma.setFabricante(request.fabricante());

        return plataforma;
    }

    public PlataformaResponse toResponse(Plataforma plataforma) {
        return new PlataformaResponse(
                plataforma.getId(),
                plataforma.getNome(),
                plataforma.getFabricante()
        );
    }
}