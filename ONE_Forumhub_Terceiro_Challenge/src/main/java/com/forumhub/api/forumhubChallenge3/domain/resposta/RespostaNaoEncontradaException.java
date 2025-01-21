package com.forumhub.api.forumhubChallenge3.domain.resposta;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

//caso alguma resposta nao for encontrada
public class RespostaNaoEncontradaException extends RuntimeException {
    private String message = "Esta resposta não foi encontrada ou não existe";
}
