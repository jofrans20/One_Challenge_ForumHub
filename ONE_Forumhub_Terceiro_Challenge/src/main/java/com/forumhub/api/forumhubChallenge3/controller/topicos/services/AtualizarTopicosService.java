package com.forumhub.api.forumhubChallenge3.controller.topicos.services;

import com.forumhub.api.forumhubChallenge3.domain.topico.*;
import com.forumhub.api.forumhubChallenge3.infra.security.OwnerValidators.TopicoOwnerValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtualizarTopicosService {
    private final TopicoRepository repository;
    private final TopicoOwnerValidation ownerValidator;

    @Autowired
    public AtualizarTopicosService(TopicoRepository repository, TopicoOwnerValidation validator) {
        this.repository = repository;
        this.ownerValidator = validator;
    }

    public TopicoDetalhamentoDTO atualizar(Long id, @Valid TopicoAtualizandoDTO dados) {
        Optional<Topico> topicoOriginal = repository.findById(id);

        if (topicoOriginal.isEmpty()) {
            throw new TopicoNaoEncontradoException();
        }

        ownerValidator.validate(topicoOriginal.get());

        topicoOriginal.get().setTitulo(dados.titulo());
        topicoOriginal.get().setMensagem(dados.mensagem());

        repository.save(topicoOriginal.get());
        return new TopicoDetalhamentoDTO(topicoOriginal.get());
    }
}
