package com.forumhub.api.forumhubChallenge3.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RespostaDTO(

        @Pattern(regexp = "\\S.*\\S", message = "A resposta não pode iniciar ou terminar com espaços em branco.")
        @NotBlank(message = "A resposta não pode ser vazia.")
        String mensagem,

        @NotNull
        Long idTopico
) {}
