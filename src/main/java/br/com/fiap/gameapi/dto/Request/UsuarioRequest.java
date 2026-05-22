package br.com.fiap.gameapi.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(
        @NotBlank(message = "Login é obrigatório")

        @Size(min = 3, max = 100,
                message = "Login deve ter entre 3 e 100 caracteres")
        String login,

        @NotBlank(message = "Senha é obrigatória")

        @Size(min = 6, max = 100,
                message = "Senha deve ter entre 6 e 100 caracteres")

        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*\\d).*$",
                message = "Senha deve conter ao menos 1 letra maiúscula e 1 número"
        )
        String senha,

        @Pattern(
                regexp = "ADMIN|USER",
                message = "Role deve ser ADMIN ou USER"
        )
        String role
) {
}
