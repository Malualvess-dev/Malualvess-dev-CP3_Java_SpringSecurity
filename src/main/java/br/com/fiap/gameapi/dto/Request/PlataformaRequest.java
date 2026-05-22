package br.com.fiap.gameapi.dto.Request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PlataformaRequest(

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 2, max = 100,
                message = "Nome deve ter entre 2 e 100 caracteres")
        String nome,

        @NotBlank(message = "Fabricante é obrigatório")
        @Size(min = 2, max = 100,
                message = "Fabricante deve ter entre 2 e 100 caracteres")

        @Pattern(
                regexp = "Sony|Microsoft|Nintendo|PC",
                message = "Fabricante deve ser Sony, Microsoft, Nintendo ou PC"
        )
        String fabricante
) {
}