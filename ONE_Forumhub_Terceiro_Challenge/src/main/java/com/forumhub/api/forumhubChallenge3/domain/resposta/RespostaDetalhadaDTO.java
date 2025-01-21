package com.forumhub.api.forumhubChallenge3.domain.resposta;

import java.time.LocalDateTime;

public record RespostaDetalhadaDTO(
        String mensagem,
        LocalDateTime data_criacao,
        String nome_autor,
        Long id_autor,
        Boolean solucao


) {
    public RespostaDetalhadaDTO(Resposta resposta) {
        this(
                resposta.getMensagem(),
                resposta.getDataCriacao(),
                resposta.getAutor().getNome(),
                resposta.getAutor().getId(),
                resposta.getSolucao()
        );
    }
}
