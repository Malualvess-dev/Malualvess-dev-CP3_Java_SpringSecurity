package br.com.fiap.gameapi.dto.Response;

public record JogoResponse(

        Long id,

        String titulo,

        String genero,

        Integer anoLancamento,

        Double preco,

        PlataformaResponse plataforma
) {
}
