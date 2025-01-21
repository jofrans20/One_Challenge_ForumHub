package com.forumhub.api.forumhubChallenge3.domain.registro;

public class EmailJaExistenteException extends RuntimeException {

    public EmailJaExistenteException() {
        super("O Email inserido já está cadastrado");
    }
}
