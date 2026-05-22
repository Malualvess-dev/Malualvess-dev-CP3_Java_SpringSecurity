package br.com.fiap.gameapi.dto.Response;

public record MensagemResponse(
        String mensagem,
        Object dados
) {
}