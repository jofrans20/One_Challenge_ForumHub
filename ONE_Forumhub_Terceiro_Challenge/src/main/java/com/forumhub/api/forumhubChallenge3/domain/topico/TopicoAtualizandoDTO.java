package com.forumhub.api.forumhubChallenge3.domain.topico;

import jakarta.validation.constraints.*;

public record TopicoAtualizandoDTO(
        //declarando mensagens para declarar o topico
        @Pattern(regexp = "\\S.*\\S", message = "O título não pode iniciar ou terminar com espaços em branco.")
        //declarando mensagem titulo do topico
        @Size(min = 5, max = 200, message = "Digite um título de 5 até 200 caracteres")
        @NotBlank
        String titulo,
        //declarando mensagem do topico
        @Size(min = 5, max = 20000, message = "Digite uma mensagem de 5 até 20.000 caracteres")
        @NotBlank
        String mensagem
) {
}