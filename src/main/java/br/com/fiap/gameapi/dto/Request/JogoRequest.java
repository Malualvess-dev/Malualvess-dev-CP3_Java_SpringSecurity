package br.com.fiap.gameapi.dto.Request;



import jakarta.validation.constraints.*;

public record JogoRequest(

        @NotBlank(message = "Título é obrigatório")
        @Size(min = 2, max = 150,
                message = "Título deve ter entre 2 e 150 caracteres")
        String titulo,

        @NotBlank(message = "Gênero é obrigatório")

        @Pattern(
                regexp = "Ação|Aventura|RPG|FPS|Esporte|Terror|Corrida",
                message = "Gênero inválido"
        )
        String genero,

        @NotNull(message = "Ano é obrigatório")

        @Min(value = 1950,
                message = "Ano deve ser maior que 1950")

        @Max(value = 2030,
                message = "Ano inválido")
        Integer anoLancamento,

        @NotNull(message = "Preço é obrigatório")

        @DecimalMin(
                value = "0.0",
                inclusive = false,
                message = "Preço deve ser maior que zero"
        )
        Double preco,

        @NotNull(message = "Plataforma é obrigatória")
        Long plataformaId
) {
}