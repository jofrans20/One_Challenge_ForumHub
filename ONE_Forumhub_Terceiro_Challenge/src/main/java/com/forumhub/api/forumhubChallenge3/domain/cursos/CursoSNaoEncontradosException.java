package com.forumhub.api.forumhubChallenge3.domain.cursos;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class CursoSNaoEncontradosException extends RuntimeException {

    private final String message = "o Curso pesquisado não foi encontrado";

    CursoSNaoEncontradosException(String mensagem) {
        super(mensagem);
    }
}
