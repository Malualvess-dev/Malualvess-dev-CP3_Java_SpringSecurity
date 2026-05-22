package br.com.fiap.gameapi.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(

        @NotBlank(message = "Login é obrigatório")

        @Size(min = 3, max = 100,
                message = "Login inválido")
        String login,

        @NotBlank(message = "Senha é obrigatória")

        @Size(min = 6, max = 100,
                message = "Senha inválida")
        String senha
) {
}
