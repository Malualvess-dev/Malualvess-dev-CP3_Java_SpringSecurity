package br.com.fiap.gameapi.dto.Response;

public record UsuarioResponse(

        Long id,

        String login,

        String role
) {
}
