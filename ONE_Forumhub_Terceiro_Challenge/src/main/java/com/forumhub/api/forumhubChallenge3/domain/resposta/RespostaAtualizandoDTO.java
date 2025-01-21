package com.forumhub.api.forumhubChallenge3.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RespostaAtualizandoDTO(

        //atualizando a resposta em caso de parametros vazios inseridos
        @Pattern(regexp = "\\S.*\\S", message = "A resposta não pode iniciar ou terminar com espaços em branco.")


        @NotBlank(message = "A resposta não pode ser vazia.")
        String mensagem
) {
}
