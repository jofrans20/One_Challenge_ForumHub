package com.forumhub.api.forumhubChallenge3.domain.topico;

import java.time.LocalDateTime;

public record TopicoDetalhamentoDTO(

        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        TopicoStatus status,
        String autor,
        String curso) {


    public TopicoDetalhamentoDTO(Topico topico) {
        this(topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor().getNome(), topico.getCursos().getNome());
    }
}
