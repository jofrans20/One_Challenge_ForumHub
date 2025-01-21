package com.forumhub.api.forumhubChallenge3.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Optional<List<Topico>> findByTituloAndMensagem(String titulo, String mensagem);
    Optional<Topico> findById(Long id);
}
