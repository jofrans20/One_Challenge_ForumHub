package com.forumhub.api.forumhubChallenge3.domain.topico;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor


public class TopicoNaoEncontradoException extends RuntimeException {
    private final String message = "O Tópico pesquisado não foi encontrado";

    TopicoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
