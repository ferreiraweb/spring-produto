package com.spring.produto.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record FornecedorDto(
        Optional<Long> id,
        @NotNull
                @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
        String nome,

        @Size(min = 3, max = 30, message = "O local deve ter entre 3 e 30 caracteres")
        String localidade,
        @Size(min = 8, max = 30, message = "O telefone deve ter entre 8 e 30 caracteres")
        String telefone
) {
}
