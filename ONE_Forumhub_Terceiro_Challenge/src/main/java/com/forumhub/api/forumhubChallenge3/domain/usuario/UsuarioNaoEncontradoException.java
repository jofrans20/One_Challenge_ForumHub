package com.forumhub.api.forumhubChallenge3.domain.usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor


public class UsuarioNaoEncontradoException extends RuntimeException {
    private final String message = "O Usuário não encontrado";

    UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
