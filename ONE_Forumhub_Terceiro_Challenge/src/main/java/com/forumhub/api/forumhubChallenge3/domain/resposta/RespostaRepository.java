package com.forumhub.api.forumhubChallenge3.domain.resposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
//query para selecionar resposta do topico verificar sql depois
    @Query("""
            select r
            from Resposta r
            where r.topico.id = :topicoId
            """)
    List<RespostaDetalhadaDTO> findAllByTopicoId(@Param("topicoId") Long topicoId);
}
