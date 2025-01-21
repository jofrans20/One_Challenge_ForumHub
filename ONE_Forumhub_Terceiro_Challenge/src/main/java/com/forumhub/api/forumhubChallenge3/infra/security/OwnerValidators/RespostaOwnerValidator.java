package com.forumhub.api.forumhubChallenge3.infra.security.OwnerValidators;

import com.forumhub.api.forumhubChallenge3.domain.resposta.Resposta;
import com.forumhub.api.forumhubChallenge3.domain.resposta.RespostaNaoEncontradaException;
import com.forumhub.api.forumhubChallenge3.domain.resposta.RespostaRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RespostaOwnerValidator implements OwnerValidator {
    private final RespostaRepository respostaRepository;


    public RespostaOwnerValidator(RespostaRepository respostaRepository) {
        this.respostaRepository = respostaRepository;
    }


    public void validate(Long idResposta) {
        Optional<Resposta> resposta = respostaRepository.findById(idResposta);
        if (resposta.isEmpty()) {
            throw new RespostaNaoEncontradaException();
        }
        if (!resposta.get().getAutor().getId().equals(authContextUserId)) {
            throw new AccessDeniedException("Não há permissão para fazer isso!");
        }
    }


    public void validate(Resposta resposta) {
        if (!resposta.getAutor().getId().equals(authContextUserId)) {
            throw new AccessDeniedException("Não há permissão para fazer isso!");
        }
    }

}

