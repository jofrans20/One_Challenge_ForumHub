package com.forumhub.api.forumhubChallenge3.infra.security.OwnerValidators;

import com.forumhub.api.forumhubChallenge3.domain.topico.Topico;
import com.forumhub.api.forumhubChallenge3.domain.topico.TopicoNaoEncontradoException;
import com.forumhub.api.forumhubChallenge3.domain.topico.TopicoRepository;
import com.forumhub.api.forumhubChallenge3.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoOwnerValidation implements OwnerValidator {
    private final TopicoRepository topicoRepository;



    @Autowired
    public TopicoOwnerValidation(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
        this.topicoRepository = topicoRepository;
    }



    public void validate(Long topicoId) {
        Optional<Topico> topicoStored = topicoRepository.findById(topicoId);

        if (topicoStored.isEmpty()) {
            throw new TopicoNaoEncontradoException();
        }
        Long idAutor = topicoStored.get().getAutor().getId();

        if (!authContextUserId.equals(idAutor)) {
            throw new AccessDeniedException("Não há permissão para fazer isso!");
        }
    }

    public void validate(Topico topico) {
        if (!authContextUserId.equals(topico.getAutor().getId())) {
            throw new AccessDeniedException("Não há permissão para fazer isso!");
        }
    }
}
